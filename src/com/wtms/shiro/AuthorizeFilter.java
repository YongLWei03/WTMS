package com.wtms.shiro;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import org.apache.shiro.web.filter.authz.AuthorizationFilter;

public class AuthorizeFilter extends AuthorizationFilter {

	@Override
	protected boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object o) throws Exception {
		//-----------------用户验证------------------
	    Subject currentUser = getSubject(request, response);
	    if (!currentUser.isAuthenticated())
	        return false;
	    //-----------------获取资源权限表达式-------------
	    SysAdmin user = (SysAdmin) currentUser.getPrincipal();
	    //request中加入attribute便于controller调用admin的信息
	    request.setAttribute("admin",user);
	    // 根据actionKey分析出权限表达式
	    HttpServletRequest hsr = ((HttpServletRequest) request);
	    String root = hsr.getContextPath();
	    String URI = hsr.getRequestURI();
	    String actionKey = URI.replace(root,"");
	    if("".equals(actionKey))
	        actionKey="/";
	    RoleService roleService = new RoleService();
	    String expression = roleService.getActionKeyExpression(actionKey);
	    //-----------------进行鉴权-------------
	    if (user==null)
	        return false;
	    else if(user.getStr("username").equals("superadmin")){
	        //超级管理员具有所有权限
	        return true;
	    }else if(expression==null){
	        return false;
	    }else if(currentUser.isPermitted(expression)){
	        //鉴权
	        return true;
	    }else{
	        return false;
	    }
	}

}
