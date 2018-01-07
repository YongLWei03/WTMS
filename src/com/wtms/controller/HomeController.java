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
		RightBean bean1 = new RightBean();
		bean1.setId(2);
		bean1.setRes("departments");
		bean1.setCreate(true);
		bean1.setDel(true);
		bean1.setDels(true);
		bean1.setUpdate(true);
		bean1.setList(true);
		bean1.setDetail(true);
		bean1.setExport(true);
		bean1.setUserId(1);
		rightsList.add(bean1);
		RightBean bean2 = new RightBean();
		bean2.setId(3);
		bean2.setRes("positions");
		bean2.setCreate(true);
		bean2.setDel(true);
		bean2.setDels(true);
		bean2.setUpdate(true);
		bean2.setList(true);
		bean2.setDetail(true);
		bean2.setExport(true);
		bean2.setUserId(1);
		rightsList.add(bean2);
		RightBean bean3 = new RightBean();
		bean3.setId(4);
		bean3.setRes("roles");
		bean3.setCreate(true);
		bean3.setDel(true);
		bean3.setDels(true);
		bean3.setUpdate(true);
		bean3.setList(true);
		bean3.setDetail(true);
		bean3.setExport(true);
		bean3.setUserId(1);
		rightsList.add(bean3);
		RightBean bean4 = new RightBean();
		bean4.setId(5);
		bean4.setRes("broles");
		bean4.setCreate(true);
		bean4.setDel(true);
		bean4.setDels(true);
		bean4.setUpdate(true);
		bean4.setList(true);
		bean4.setDetail(true);
		bean4.setExport(true);
		bean4.setUserId(1);
		rightsList.add(bean4);
		RightBean bean5 = new RightBean();
		bean5.setId(6);
		bean5.setRes("faults");
		bean5.setCreate(true);
		bean5.setDel(true);
		bean5.setDels(true);
		bean5.setUpdate(true);
		bean5.setList(true);
		bean5.setDetail(true);
		bean5.setExport(true);
		bean5.setUserId(1);
		rightsList.add(bean5);
		RightBean bean6 = new RightBean();
		bean6.setId(7);
		bean6.setRes("fstates");
		bean6.setCreate(true);
		bean6.setDel(true);
		bean6.setDels(true);
		bean6.setUpdate(true);
		bean6.setList(true);
		bean6.setDetail(true);
		bean6.setExport(true);
		bean6.setUserId(1);
		rightsList.add(bean6);
		RightBean bean7 = new RightBean();
		bean7.setId(8);
		bean7.setRes("workTicketStates");
		bean7.setCreate(true);
		bean7.setDel(true);
		bean7.setDels(true);
		bean7.setUpdate(true);
		bean7.setList(true);
		bean7.setDetail(true);
		bean7.setExport(true);
		bean7.setUserId(1);
		rightsList.add(bean7);
		RightBean bean8 = new RightBean();
		bean8.setId(9);
		bean8.setRes("operateTicketStates");
		bean8.setCreate(true);
		bean8.setDel(true);
		bean8.setDels(true);
		bean8.setUpdate(true);
		bean8.setList(true);
		bean8.setDetail(true);
		bean8.setExport(true);
		bean8.setUserId(1);
		rightsList.add(bean8);
		RightBean bean9 = new RightBean();
		bean9.setId(10);
		bean9.setRes("kks");
		bean9.setCreate(true);
		bean9.setDel(true);
		bean9.setDels(true);
		bean9.setUpdate(true);
		bean9.setList(true);
		bean9.setDetail(true);
		bean9.setExport(true);
		bean9.setUserId(1);
		rightsList.add(bean9);
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
