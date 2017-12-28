package com.wtms.controller;

import java.util.HashMap;
import java.util.List;

import com.jfinal.core.Controller;
import com.wtms.bean.MessageBean;
import com.wtms.common.model.Department;
import com.wtms.common.model.Position;
import com.wtms.common.model.Role;
import com.wtms.service.RoleService;

public class RoleController extends Controller{
	static RoleService roleService = new RoleService();
	public void total() {
		Integer totalCount = roleService.total();
		HashMap<String,Integer> data= new HashMap<String,Integer>();
		data.put("total", totalCount);
		renderJson(new MessageBean().setCode(1).setMessage("系统角色_查询全部").setData(data));
	}
	
	public void index() {
		Integer page = getParaToInt("_page");
		Integer limit = getParaToInt("_limit");
		List<Role> positions = roleService.findAll(page,limit).getList();
		renderJson(new MessageBean().setCode(1).setMessage("系统角色_查询全部").setData(positions));
	}

	public void query() {
		List<Role> roles = roleService.query();
		renderJson(new MessageBean().setCode(1).setMessage("系统角色_查询全部").setData(roles));
	}
}
