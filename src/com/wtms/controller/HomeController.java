package com.wtms.controller;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;

import com.jfinal.core.Controller;
import com.jfinal.kit.HashKit;
import com.wtms.bean.MessageBean;

public class HomeController extends Controller{
	
	public void login() {
		if(Boolean.FALSE.equals(validateCaptcha("captcha"))) {
			renderJson(new MessageBean().setCode("1").setMessage("验证码错误"));
			return;
		}
		String username = getPara("username");
		String password = HashKit.md5(getPara("password"));
		Subject subject = SecurityUtils.getSubject();
		UsernamePasswordToken token = new UsernamePasswordToken(username,password);
		try {
			subject.login(token);
			renderJson(new MessageBean().setCode("1").setMessage("登录成功"));
		} catch (AuthenticationException e) {
			 renderJson(new MessageBean().setCode("0").setMessage("登录失败"));
		}
	}
	
	
	public void logout() {
		Subject subject = SecurityUtils.getSubject();
		subject.logout();
		redirect("/passport/login");
		
	}
	
}
