package com.wtms.controller;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.jfinal.aop.Before;
import com.jfinal.core.Controller;
import com.jfinal.kit.HttpKit;
import com.jfinal.plugin.activerecord.Record;
import com.jfinal.plugin.jwttoken.JwtTokenInterceptor;
import com.wtms.bean.MessageBean;
import com.wtms.bean.UserBean;
import com.wtms.common.model.Brole;
import com.wtms.common.model.Role;
import com.wtms.common.model.User;
import com.wtms.service.BroleService;
import com.wtms.service.RoleService;
import com.wtms.service.UserService;

/**
 * @author guoce
 *
 */
public class UserController extends Controller{
	static UserService userService = new UserService();
	static RoleService roleService = new RoleService();
	static BroleService broleService = new BroleService();
	@Before(JwtTokenInterceptor.class)
	public void total() {
		Integer totalCount = userService.total();
		HashMap<String,Integer> data= new HashMap<String,Integer>();
		data.put("total", totalCount);
		renderJson(new MessageBean().setCode(1).setMessage("人员管理_查询全部").setData(data));
	}
	
	public void query() {
		Integer userId = getParaToInt(0);
		if(userId != null){
			detail(userId);
		}else{
			Integer page = getParaToInt("_page");
			Integer limit = getParaToInt("_limit");
			List<Record> users = userService.findAll(page,limit).getList();
			for (Record user : users) {
				List<Role> roles= roleService.getRolesByUserId(user.get("id"));
	//			UserBean userBean = (UserBean) UserService.store.get(user.get("username"));   //服务器缓存用户信息后
				if(roles.size()>0){
					user.set("roles", roles);
				}else{
					user.set("roles", "");
				}
				String broleIds = user.getStr("broleIds");
				List<Brole> broles = broleService.getBrolesByBroleIds(broleIds);
				List<String> brolenames = broles.stream().map(Brole::getName).collect(Collectors.toList());
				user.set("broleIds", String.join(" | ", brolenames));
			}
			renderJson(new MessageBean().setCode(1).setMessage("人员管理_查询全部").setData(users));
		}
	}
	public void detail(int userId) {
		Record user = userService.findUserInfoById(userId);
		List<Role> roles = roleService.getRolesByUserId(userId);
		user.set("roles", roles);
		renderJson(new MessageBean().setCode(1).setMessage("人员管理_查询单条").setData(user));
	}
	
	public void create() {
		JSONObject para = JSON.parseObject(HttpKit.readData(getRequest()));
		User user = new User().setUsername(para.getString("username")).setPwd(para.getString("pwd")).setName(para.getString("username"))
		.setWorknum(para.getString("worknum")).setSex(para.getString("sex")).setDepartmentIds(para.getString("departmentIds"))
		.setBroleIds(para.getString("broleIds")).setRoleIds(para.getString("roleIds"));
		Boolean isOk= user.save();
		Integer userid = user.getId();
		String[] roleids = para.getString("roleIds").split(",");
		//todo 中间表中加入关联
		roleService.createRolesForUser(userid,roleids);
		renderJson(new MessageBean().setCode(1).setMessage("人员管理_新增").setData(isOk));
	}
	
	public void update() {
		JSONObject para = JSON.parseObject(HttpKit.readData(getRequest()));
		Integer userId = para.getInteger("id");
		new User().setId(para.getInteger("id")).setUsername(para.getString("username")).setPwd(para.getString("pwd")).setName(para.getString("username"))
		.setWorknum(para.getString("worknum")).setSex(para.getString("sex")).setDepartmentIds(para.getString("departmentIds"))
		.setBroleIds(para.getString("broleIds")).setRoleIds(para.getString("roleIds")).update();
		String[] roleids = para.getString("roleIds").split(",");
		//todo 中间表中更新关联
		roleService.deleteByUserId(userId);
		roleService.createRolesForUser(userId,roleids);
		renderJson(new MessageBean().setCode(1).setMessage("人员管理_更新"));
	}
	
	/**
	 * 删除用户，先删除各种关系 userRole userMenu 
	 */
	public void delete() {
		Integer userId = getParaToInt(0);
		roleService.deleteByUserId(userId);
		new User().setId(userId).delete();
		renderJson(new MessageBean().setCode(1).setMessage("人员管理_删除"));
	}
}
