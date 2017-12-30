package com.wtms.service;

import java.util.List;

import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Page;
import com.jfinal.plugin.activerecord.Record;
import com.wtms.common.model.Brole;
import com.wtms.common.model.Position;
import com.wtms.common.model.Role;
import com.wtms.common.model.UserRole;

/**
 * @author guoce
 *
 */
public class RoleService {
	
	private static final Role dao = new Role().dao();
	
	public Page<Role> paginate(int pageNumber, int pageSize) {
		return dao.paginate(pageNumber, pageSize, "select *", "from wf_role order by id asc");
	}
	
	public Role findById(int id) {
		return dao.findById(id);
	}
	
	public void deleteById(int id) {
		dao.deleteById(id);
	}
	
	public void addUser() {
		new Role().setName("管理员").save();
	}
	/**
	 * 根据用户名获取角色
	 */
	public List<Role> getByUsername(String username) {
		return Role.dao.find("SELECT r.* FROM wf_role r,wf_user_role ur,wf_user u where r.id = ur.role_id and ur.user_id = u.id and u.username = '"+username+"'");
	}
	/**
	 * 获取全部角色
	 */
	public List<Role> findAll() {
		return Role.dao.find("SELECT r.* FROM wf_role r");
	}

	public Integer total(){
		Integer totalCount = Db.queryInt("select count(*) from wf_brole");
		return totalCount;
	}
	public Page<Role> findAll(int page,int limit){
		Page<Role> broles = dao.paginate(page, limit,"select id,name "," from wf_brole");
		return broles;
	}
	
	public List<Role> query(){
		return dao.find("select wd.id,wd.name from wf_brole wd");
	}
	
	public List<Role> getRolesByUserId(Integer userId) {
		return dao.find("SELECT wr.id,wr.name FROM wf.wf_user_role wur,wf_role wr where wr.id = wur.role_id and wur.user_id = '"+userId+"'");
	}

	public boolean createRolesForUser(Integer userid, String[] roleids) {
		for (String roleId : roleids) {
			System.out.println(userid+"  "+roleId);
			new UserRole().setUserId(userid).setRoleId(Integer.parseInt(roleId)).save();
		}
		return true;
	}

	public boolean deleteByUserId(Integer userId) {
		Db.delete("delete from wf_user_role where user_id="+userId);
		return true;
	}
	

}
