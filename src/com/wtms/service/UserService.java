package com.wtms.service;

import java.util.Arrays;
import java.util.List;

import com.jfinal.kit.Kv;
import com.jfinal.plugin.activerecord.Page;
import com.jfinal.plugin.jwttoken.IJwtAble;
import com.jfinal.plugin.jwttoken.IJwtUserService;
import com.wtms.bean.UserBean;
import com.wtms.common.model.User;

public class UserService implements IJwtUserService{
	public static final UserService me = new UserService();
	private static final User dao = new User().dao();
	private static Kv store = Kv.create();  //缓存所有用户角色和权限
	static {
        store.set("admin",
                new UserBean().setForces(Arrays.asList("登录后台", "管理用户"))
                        .setRoles(Arrays.asList("管理员", "普通用户")).setUserName("admin").setPassword("123456")
        ).set("user",
                new UserBean().setForces(Arrays.asList("前台登录", "发布文章"))
                        .setRoles(Arrays.asList("普通用户")).setUserName("user").setPassword("123456")
        );
    }
	public Page<User> paginate(int pageNumber, int pageSize) {
		return dao.paginate(pageNumber, pageSize, "select *", "from wf_user order by id asc");
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
		if (user != null)
            return user;
        throw new RuntimeException("找不到用户");
	}

}
