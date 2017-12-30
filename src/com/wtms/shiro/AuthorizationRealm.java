package com.wtms.shiro;

import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
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
import com.wtms.common.model.Permission;
import com.wtms.common.model.Role;
import com.wtms.common.model.User;
import com.wtms.service.PermissionService;
import com.wtms.service.RoleService;
import com.wtms.service.UserService;

/**
 * @author guoce
 *
 */
public class AuthorizationRealm extends AuthorizingRealm {
	static RoleService roleService = new RoleService();
	static PermissionService permissionService = new PermissionService();
	static UserService userService = new UserService();
	/** 
     * 添加角色 
     * @param username 
     * @param info 
     */  
    private void addRole(String username, SimpleAuthorizationInfo info) {  
        List<Role> roles = roleService.getByUsername(username);  
        if(roles!=null&&roles.size()>0){  
            for (Role role : roles) {  
                info.addRole(role.getName());  
            }  
        }  
    }  
    
    /** 
     * 添加权限 
     * @param username 
     * @param info 
     * @return 
     */  
    private SimpleAuthorizationInfo addPermission(String username,SimpleAuthorizationInfo info) {  
        List<Permission> permissions = permissionService.findPermissionByName(username);  
        for (Permission permission : permissions) {  
            info.addStringPermission(permission.getUrl());//添加权限    
        }  
        return info;    
    }  
	/**
	 * 获取用户授权信息
	 * @param principals 用户身份
	 * @return null or 授权信息
	 */
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
		//用户名
		String username = (String) principals.fromRealm(getName()).iterator().next();
		//根据用户名来添加相应的权限和角色
		if(StringUtils.isEmpty(username)) {
			SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
			addPermission(username, info);
			addRole(username, info);
			return info;
		}
		return null;
		
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
		User admin = userService.getByUsername(token.getUsername());
	    if (admin != null) {
	        if(!admin.getPwd().equals(String.valueOf(token.getPassword()))){
	            throw new AuthenticationException("密码错误");
	        }
	        //增加用户登录判断
//	        Db.update("update wf_user set loginTime=?,loginCount=loginCount+1 where id=?",new Date(),admin.getId());
	        return new SimpleAuthenticationInfo(admin, admin.getPwd(),admin.getUsername());
	    } else {
	        throw new AuthenticationException("用户不存在");
	    }
	}
	
}
