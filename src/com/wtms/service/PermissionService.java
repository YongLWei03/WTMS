package com.wtms.service;

import java.util.List;

import com.jfinal.plugin.activerecord.Page;
import com.wtms.common.model.Permission;

public class PermissionService {
	
	private static final Permission dao = new Permission().dao();
	
	public Page<Permission> paginate(int pageNumber, int pageSize) {
		return dao.paginate(pageNumber, pageSize, "select *", "from wf_role order by id asc");
	}
	
	public Permission findById(int id) {
		return dao.findById(id);
	}
	
	public void deleteById(int id) {
		dao.deleteById(id);
	}
	
	public void addPermission() {
		new Permission().setPermission("user").setName("用户管理").save();
	}

	public List<Permission> findPermissionByName(String username) {
		return Permission.dao.find(  " SELECT  "
									+"     p.* "
									+" FROM "
									+"     wf_user u, "
									+"     wf_role r, "
									+"     wf_user_role ur, "
									+"     wf_permission p, "
									+"     wf_role_permission rp "
									+" WHERE "
									+"     u.id = ur.user_id AND ur.role_id = r.id "
									+"         AND rp.role_id = r.id "
									+"         AND rp.permission_id = p.id "
									+"         AND u.username = '"+username+"' "
									+"         AND permission IS NOT NULL ");
	}

	/**
	 * @param actionKey
	 * @return
	 * 更具传入的url查找权限名
	 */
	public String getActionKeyExpression(String actionKey) {
		//是不是要加模糊匹配，因为有一级菜单
		return Permission.dao.find("SELECT * FROM wf_permission wf where wf.url = '"+actionKey+"'").get(0).getPermission();
	}
}
