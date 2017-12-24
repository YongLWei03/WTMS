package com.wtms.service;

import java.util.List;

import com.wtms.common.model.Menu;

public class MenuService{
	private static final Menu dao = new Menu().dao();
	
	public List<Menu> findParentByUserName(String userName) {
		System.out.println(" select m.* from wf_menu m,wf_role r,wf_user u,wf_user_role ur,wf_role_menu rm  "
				+" where u.id = ur.user_id  "
				+" and ur.role_id = r.id  "
				+" and rm.role_id = r.id  "
				+" and rm.menu_id = m.id  "
				+" and u.username = '"+userName+"' "
				+" and m.parentid = 0");
		return Menu.dao.find(" select m.* from wf_menu m,wf_role r,wf_user u,wf_user_role ur,wf_role_menu rm  "
				+" where u.id = ur.user_id  "
				+" and ur.role_id = r.id  "
				+" and rm.role_id = r.id  "
				+" and rm.menu_id = m.id  "
				+" and u.username = '"+userName+"' "
				+" and m.parentid = 0");
	}
	
	public List<Menu> findChildByParentid(String userName,Integer parentId) {
		return Menu.dao.find(" select m.* from wf_menu m,wf_role r,wf_user u,wf_user_role ur,wf_role_menu rm  "
				+" where u.id = ur.user_id  "
				+" and ur.role_id = r.id  "
				+" and rm.role_id = r.id  "
				+" and rm.menu_id = m.id  "
				+" and u.username = '"+userName+"' "
				+" and m.parentid = "+parentId);
	}
}
