package com.wtms.controller;

import java.util.HashMap;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;

import com.jfinal.core.Controller;
import com.jfinal.kit.HttpKit;
import com.jfinal.kit.JsonKit;
import com.jfinal.kit.Ret;
import com.jfinal.plugin.jwttoken.JwtKit;
import com.wtms.bean.MessageBean;
import com.wtms.bean.UserBean;
import com.wtms.service.UserService;

public class HomeController extends Controller{
	static UserService userService = new UserService();
	
	public void login() {
		getResponse().addHeader("Access-Control-Allow-Origin", "*");
		String username = getPara("username");
		String password = getPara("password");
		String token = JwtKit.getToken(username, password);
	    renderJson(Ret.by("token", token));
	    renderJson(new MessageBean().setCode(1).setMessage("登录成功").setData(Ret.by("token", token)));
		/*Subject subject = SecurityUtils.getSubject();
		UsernamePasswordToken token = new UsernamePasswordToken(username,password);
		try {
			subject.login(token);
			Session session = subject.getSession();
			HashMap<String, String> sessionId = new HashMap<String,String>();
			sessionId.put("token", session.getId().toString());
			
			renderJson(new MessageBean().setCode(1).setMessage("登录成功").setData(JsonKit.toJson(sessionId)));
		} catch (AuthenticationException e) {
			System.out.println(e.getMessage());
			renderJson(new MessageBean().setCode(0).setMessage("登录失败"));
		}*/
	}
	
	
	public void logout() {
		System.out.println(HttpKit.readData(getRequest()));
		Subject subject = SecurityUtils.getSubject();
		subject.logout();
		redirect("/login");
	}
	
	
}
