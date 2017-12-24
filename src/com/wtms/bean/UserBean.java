package com.wtms.bean;

import java.util.Date;
import java.util.List;

import com.jfinal.plugin.jwttoken.IJwtAble;

public class UserBean implements IJwtAble {
	
	private String userName;
	 
    private String password;
 
    private List<String> _roles;
    
    String role1 ="user:query,edit";
 
    private List<String> _forces;
    
    /**
     * 当前用户的角色
     * @return
     */
	@Override
	public List<String> getRoles() {
		// 使用的时候通过数据库查询返回给插件一个角色集合
        return get_roles();
	}
	/**
     * 当前用户的权限
     * @return
     */
	@Override
	public List<String> getForces() {
		// 使用的时候通过数据库查询返回给插件一个权限集合
        return get_forces();
	}

	@Override
	public Date getLastModifyPasswordTime() {
		return new Date(System.currentTimeMillis() - 60L * 1000L * 60L * 24);
	}
	public String getUserName() {
		return userName;
	}
	public UserBean setUserName(String userName) {
		this.userName = userName;
		return this;
	}
	public String getPassword() {
		return password;
	}
	public UserBean setPassword(String password) {
		this.password = password;
		return this;
	}
	public List<String> get_roles() {
		return _roles;
	}

	public List<String> get_forces() {
		return _forces;
	}
    public UserBean setRoles(List<String> roles) {
        this._roles = roles;
        return this;
    }
 
    public UserBean setForces(List<String> forces) {
        this._forces = forces;
        return this;
    }
}
