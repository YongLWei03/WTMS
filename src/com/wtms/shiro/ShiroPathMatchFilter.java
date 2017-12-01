package com.wtms.shiro;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.AccessControlFilter;

import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Multimap;
import com.jfinal.log.Log;
import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Record;
import com.wtms.common.model.Role;
import com.wtms.service.RoleService;

public class ShiroPathMatchFilter extends AccessControlFilter {
	static RoleService roleService = new RoleService();
	private static final Log log =  Log.getLog(ShiroPathMatchFilter.class);  
    private static Multimap<String,String> allPermissions = ArrayListMultimap.create();  
    private static List<String> allRoles = new ArrayList<String>();
    
    public static void initUrlMaps(){  
    	  
        log.info("start initializing permission maps.");  
        // 缓存所有角色  
        allRoles.clear();  
        List<Role> roles =roleService.findAll();  
        for(Role secRole:roles){  
            allRoles.add(secRole.getStr("role_name"));  
        }  
        // 缓存所有权限  
        allPermissions.clear();  
        List<Record> rolePermissions = Db.find("select r.role_name,p.permission " +  
                "from sec_role r,sec_permission p,sec_role_permission rp " +  
                "where rp.role_id=r.id and rp.permission_id=p.id and permission is not null ");  
        for(Record rolePermission :rolePermissions){  
            allPermissions.put(rolePermission.getStr("role_name"),rolePermission.getStr("permission"));  
        }  
        log.info("finished permissions map with entries:" + allPermissions.size());  
    }  
  
    public boolean isAccessAllowed(Subject subject,String path){  
        if(allPermissions.isEmpty()){  
            initUrlMaps();  
        }  
        for(String role : allRoles){  
            if(subject.hasRole(role)){  
                for(String url:allPermissions.get(role)){  
                    if(pathsMatch(url, path)){  
                        return true;  
                    }  
                }  
            }  
        }  
        return false;  
    }  
    
	@Override
	protected boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object o) throws Exception {
		if(allPermissions.isEmpty()){  
            initUrlMaps();  
        }  
        Subject subject = getSubject(request, response);  
        for(String role : allRoles){  
            if(subject.hasRole(role)){  
                for(String url:allPermissions.get(role)){  
                    if(pathsMatch(url, request)){  
                        return true;  
                    }  
                }  
            }  
        }  
        return false;  
	}

	@Override
	protected boolean onAccessDenied(ServletRequest request, ServletResponse response) throws Exception {
		log.info("onAccessDenied");  
        setLoginUrl("/auth/login");  
        redirectToLogin(request,response);  
        return false;  
	}

}
