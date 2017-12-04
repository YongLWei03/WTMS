package com.wtms.controller;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;

import com.jfinal.core.Controller;
import com.jfinal.kit.HashKit;
import com.jfinal.kit.HttpKit;
import com.wtms.bean.MessageBean;
import com.wtms.common.model.User;
import com.wtms.service.UserService;

public class HomeController extends Controller{
	static UserService userService = new UserService();
	
	public void login() {
//		if(Boolean.FALSE.equals(validateCaptcha("captcha"))) {
//			renderJson(new MessageBean().setCode("1").setMessage("验证码错误"));
//			return;
//		}
//		String jsonStr = HttpKit.readData(getRequest());
		String username = getPara("username");
		String password = HashKit.md5(getPara("password"));
		Subject subject = SecurityUtils.getSubject();
		UsernamePasswordToken token = new UsernamePasswordToken(username,password);
		try {
			subject.login(token);
			renderJson(new MessageBean().setCode("1").setMessage("登录成功"));
		} catch (AuthenticationException e) {
			System.out.println(e.getMessage());
			renderJson(new MessageBean().setCode("0").setMessage("登录失败"));
		}
	}
	
	
	public void logout() {
		Subject subject = SecurityUtils.getSubject();
		subject.logout();
		redirect("/passport/login");
		
	}
	
}
