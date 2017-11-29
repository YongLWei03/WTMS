package com.demo.shiro;

import java.util.Date;
import java.util.List;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;

import com.jfinal.plugin.activerecord.Db;

/**
 * @author guoce
 *
 */
public class AuthorizationRealm extends AuthorizingRealm {
	/**
	 * 获取用户授权信息
	 * @param principals 用户身份
	 * @return null or 授权信息
	 */
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection arg0) {
		SysAdmin userInPrincipal = (SysAdmin) principals.getPrimaryPrincipal();
	    //根据用户获取权限
	    List<String> stringPermissions = AdminService.getPermissions(userInPrincipal.getId());
	    SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
	    //stringPermissions结构：
	    //user
	    //user:list
	    //user:add
	    //user:edit
	    //...
	    info.addStringPermissions(stringPermissions);
	    return info;
	}
	 /**
	 * 获取用户验证信息
	 * @param authcToken 所需验证的token
	 * @return null or 身份信息
	 * @throws AuthenticationException 验证异常
	 */
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(
			AuthenticationToken authcToken) throws AuthenticationException {
		UsernamePasswordToken token = (UsernamePasswordToken) authcToken;
	    SysAdmin admin = AdminService.getByUsername(token.getUsername());
	    if (admin != null) {
	        if(!admin.getPassword().equals(String.valueOf(token.getPassword()))){
	            throw new AuthenticationException("密码错误");
	        }
	        Db.update("update sys_admin set loginTime=?,loginCount=loginCount+1 where id=?",new Date(),admin.getId());
	        return new SimpleAuthenticationInfo(admin, admin.getPassword(),admin.getUsername());
	    } else {
	        throw new AuthenticationException("用户不存在");
	    }
	}

}
