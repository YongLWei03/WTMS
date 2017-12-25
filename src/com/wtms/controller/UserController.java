package com.wtms.controller;

import java.util.HashMap;
import java.util.List;

import com.jfinal.aop.Before;
import com.jfinal.core.Controller;
import com.jfinal.plugin.activerecord.Record;
import com.jfinal.plugin.jwttoken.JwtTokenInterceptor;
import com.wtms.bean.MessageBean;
import com.wtms.service.UserService;

public class UserController extends Controller{
	static UserService userService = new UserService();
	@Before(JwtTokenInterceptor.class)
	public void total() {
		Integer totalCount = userService.total();
		HashMap<String,Integer> data= new HashMap<String,Integer>();
		data.put("total", totalCount);
		renderJson(new MessageBean().setCode(1).setMessage("人员管理_查询全部").setData(data));
	}
	
	public void index() {
		Integer page = getParaToInt("_page");
		Integer limit = getParaToInt("_limit");
		List<Record> users = userService.findAll(page,limit).getList();
		renderJson(new MessageBean().setCode(1).setMessage("人员管理_查询全部").setData(users));
	}
}
