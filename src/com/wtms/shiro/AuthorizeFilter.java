package com.wtms.shiro;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.authz.AuthorizationFilter;

import com.wtms.common.model.User;
import com.wtms.service.PermissionService;

public class AuthorizeFilter extends AuthorizationFilter {
	
	static PermissionService permissionService = new PermissionService();

	@Override
	protected boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object o) throws Exception {
		/*//-----------------用户验证------------------
	    Subject currentUser = getSubject(request, response);
	    if (!currentUser.isAuthenticated())
	        return false;
	    //-----------------获取资源权限表达式-------------
	    User user = (User) currentUser.getPrincipal();
	    //request中加入attribute便于controller调用admin的信息
	    request.setAttribute("admin",user);
	    // 根据actionKey分析出权限表达式
	    HttpServletRequest hsr = ((HttpServletRequest) request);
	    String root = hsr.getContextPath();
	    String URI = hsr.getRequestURI();
	    String actionKey = URI.replace(root,"");
	    if("".equals(actionKey))
	        actionKey="/";
	    
	    String expression = permissionService.getActionKeyExpression(actionKey);
	    //-----------------进行鉴权-------------
	    if (user==null)
	        return false;
	    else if(user.getStr("username").equals("admin")){
	        //超级管理员具有所有权限
	        return true;
	    }else if(expression==null){
	        return false;
	    }else if(currentUser.isPermitted(expression)){
	        //鉴权
	        return true;
	    }else{
	        return false;
	    }*/
		return true;
	}

}
