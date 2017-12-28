package com.wtms.service;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import com.jfinal.kit.Kv;
import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Page;
import com.jfinal.plugin.activerecord.Record;
import com.jfinal.plugin.jwttoken.IJwtAble;
import com.jfinal.plugin.jwttoken.IJwtUserService;
import com.wtms.bean.UserBean;
import com.wtms.common.model.Permission;
import com.wtms.common.model.Role;
import com.wtms.common.model.User;

public class UserService implements IJwtUserService{
	private static final User dao = new User().dao();
	private static Kv store = Kv.create();  //缓存所有用户角色和权限
	static PermissionService permissionService = new PermissionService();
	static RoleService roleService = new RoleService();
	public static void addUserInfo() {
		//加载所有用户信息和权限
        /*store.set("admin",
                new UserBean().setForces(Arrays.asList("登录后台", "管理用户"))
                        .setRoles(Arrays.asList("管理员", "普通用户")).setUserName("admin").setPassword("123456")
        ).set("user",
                new UserBean().setForces(Arrays.asList("前台登录", "发布文章"))
                        .setRoles(Arrays.asList("普通用户")).setUserName("user").setPassword("123456")
        );*/
		List<User> users = User.dao.find("select * from wf_user");
		for (User user : users) {
			List<Role> roles = roleService.getByUsername(user.getName());
			List<Permission> permissions = permissionService.findPermissionByName(user.getName());  
			List<String> roles_v = roles.stream().map(Role::getRoleName).collect(Collectors.toList()); 
			List<String> forces_v = permissions.stream().map(Permission::getPermission).collect(Collectors.toList());
			store.set(user.getUsername(),new UserBean().setRoles(roles_v).setForces(forces_v).setUserName(user.getUsername()).setPassword(user.getPwd()));
		}
    }
	
	public Integer total(){
		Integer totalCount = Db.queryInt("select count(*) from wf_user");
		return totalCount;
	}
	public Page<Record> findAll(int page,int limit){
		Page<Record> users = Db.paginate(page, limit, "select wu.*,wp.name positionName,wd.name departmentName",
				"from wf_user wu,wf_position wp,wf_department wd where wu.departmentId = wd.id and wu.positionId = wp.id");
		return users;
	}
	public Record findUserInfoById(int id) {
		return Db.findFirst("select wu.*,wp.name positionName,wd.name departmentName from wf_user wu,wf_position wp,wf_department wd where wu.departmentId = wd.id and wu.positionId = wp.id and wu.id="+id);
	}
	
	public User findById(int id) {
		return dao.findById(id);
	}
	
	public void deleteById(int id) {
		dao.deleteById(id);
	}
	
	public void addUser() {
		new User().setName("guoce").setPositionId("1").save();
	}

	public User getByUsername(String username) {
		List<User> userList = dao.find("select * from wf_user where username='"+username+"'");
		return userList.get(0);
	}

	@Override
	public IJwtAble login(String userName, String password) {
		UserBean user = (UserBean) store.get(userName);
		if (user != null && user.getPassword().equals(password)) {
			return user;
		} else if (user == null) {
			throw new RuntimeException("找不到该用户");
		} else if (!user.getPassword().equals(password)) {
			throw new RuntimeException("密码错误");
		}
		return null;
	}

}
