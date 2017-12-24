package com.wtms.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.util.WebUtils;

import com.alibaba.fastjson.JSON;
import com.jfinal.aop.Before;
import com.jfinal.core.Controller;
import com.jfinal.json.Jackson;
import com.jfinal.json.JacksonFactory;
import com.jfinal.json.Json;
import com.jfinal.kit.HttpKit;
import com.jfinal.kit.Ret;
import com.jfinal.plugin.jwttoken.Auth;
import com.jfinal.plugin.jwttoken.JwtKit;
import com.jfinal.plugin.jwttoken.JwtTokenInterceptor;
import com.wtms.bean.MessageBean;
import com.wtms.bean.RightBean;
import com.wtms.bean.UserBean;
import com.wtms.common.model.User;
import com.wtms.service.UserService;

public class HomeController extends Controller{
	static UserService userService = new UserService();
	
	public void login() {
		String username = getPara("username");
		String password = getPara("password");
		String token = null;
		try{
			token = JwtKit.getToken(username, password);
			renderJson(new MessageBean().setCode(1).setMessage("登录成功").setData(Ret.by("token", token)));
		}catch(Exception e){
			renderJson(new MessageBean().setCode(0).setMessage(e.getMessage()));
		}
	}
	
	public void logout() {
		System.out.println(HttpKit.readData(getRequest()));
		Subject subject = SecurityUtils.getSubject();
		subject.logout();
		redirect("/login");
	}
	
	//返回用户信息和权限
	@Before(JwtTokenInterceptor.class)
//	@Auth(hasRoles="管理")
	public void info(){
		UserBean me = (UserBean)getAttr("me");
		System.out.println(me.getUserName());
//		User user = userService.getByUsername(me.getUserName());
//		renderJson(new MessageBean().setCode(1).setMessage("用户信息").setData(Jackson.getJson().toJson(user)));
		HashMap<String, Object> userInfoMap = new HashMap<String,Object>();
		List<RightBean> rightsList = new ArrayList<RightBean>();
		userInfoMap.put("username", me.getUserName());
		userInfoMap.put("pwd", "111");
		userInfoMap.put("worknum", "321");
		userInfoMap.put("phone", "1234567");
		userInfoMap.put("name", "管理员");
		userInfoMap.put("sex", "man");
		userInfoMap.put("departmentId", 1);
		userInfoMap.put("positionId", 15);
		userInfoMap.put("roleId", 2);
		userInfoMap.put("id", 5);
		userInfoMap.put("avatar", "https://apic.douyucdn.cn/upload/avatar/face/201607/24/71fdc2fdb425fa36ba40e6458466c90c_big.jpg");
		RightBean bean = new RightBean();
		bean.setId(1);
		bean.setRes("users");
		bean.setCreate(true);
		bean.setDel(true);
		bean.setDels(true);
		bean.setUpdate(true);
		bean.setList(true);
		bean.setDetail(true);
		bean.setExport(true);
		bean.setUserId(1);
		rightsList.add(bean);
		userInfoMap.put("rights", rightsList);
		renderJson(new MessageBean().setCode(1).setMessage("用户信息").setData(userInfoMap));
		
	}

	
	public static void main(String[] args) {
		HashMap<String, Object> userInfoMap = new HashMap<String,Object>();
		List<RightBean> rightsList = new ArrayList<RightBean>();
		userInfoMap.put("username", "admin");
		userInfoMap.put("pwd", "111");
		userInfoMap.put("worknum", "321");
		userInfoMap.put("phone", "1234567");
		userInfoMap.put("name", "管理员");
		userInfoMap.put("sex", "man");
		userInfoMap.put("departmentId", 1);
		userInfoMap.put("positionId", 15);
		userInfoMap.put("roleId", 2);
		userInfoMap.put("id", 5);
		userInfoMap.put("avatar", "https://apic.douyucdn.cn/upload/avatar/face/201607/24/71fdc2fdb425fa36ba40e6458466c90c_big.jpg");
		RightBean bean = new RightBean();
		bean.setId(1);
		bean.setRes("users");
		bean.setCreate(true);
		bean.setDel(true);
		bean.setDels(true);
		bean.setUpdate(true);
		bean.setList(true);
		bean.setDetail(true);
		bean.setExport(true);
		bean.setUserId(1);
		rightsList.add(bean);
		userInfoMap.put("rights", rightsList);
		System.out.println(JSON.toJSONString(userInfoMap));
	}
	
	
}
