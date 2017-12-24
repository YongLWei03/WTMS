package com.wtms.controller;

import java.util.ArrayList;
import java.util.List;

import com.jfinal.aop.Before;
import com.jfinal.core.Controller;
import com.jfinal.plugin.jwttoken.JwtTokenInterceptor;
import com.wtms.bean.ChildBean;
import com.wtms.bean.MenuBean;
import com.wtms.bean.MessageBean;
import com.wtms.bean.UserBean;
import com.wtms.common.model.Menu;
import com.wtms.service.MenuService;

public class MenuController extends Controller{
	static MenuService menuService = new MenuService();
	@Before(JwtTokenInterceptor.class)
	public void query() {
		UserBean me = (UserBean)getAttr("me");
		List<MenuBean> menuBeans = new ArrayList<MenuBean>();
		List<Menu> menus = menuService.findParentByUserName(me.getUserName());
		for (Menu menu : menus) {
			List<Menu> childs = menuService.findChildByParentid(me.getUserName(),menu.getId());
			MenuBean mb = new MenuBean().setId(menu.getId()).setName(menu.getName());
			List<ChildBean> childBeans =  new ArrayList<ChildBean>();
			for (Menu child:childs) {
				childBeans.add(new ChildBean().setId(child.getId()).setMenuId(menu.getId()).setName(child.getName()));
			}
			mb.setChildren(childBeans);
			menuBeans.add(mb);
		}
		renderJson(new MessageBean().setCode(1).setMessage("数据错误").setData(menuBeans));
	}
}
