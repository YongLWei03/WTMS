package com.wtms.service;

import java.util.List;

import com.jfinal.plugin.activerecord.Page;
import com.wtms.common.model.Role;

/**
 * @author guoce
 *
 */
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
		new Role().setRoleName("管理员").save();
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

	

}
