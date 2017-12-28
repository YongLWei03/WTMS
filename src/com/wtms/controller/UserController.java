package com.wtms.controller;

import java.util.HashMap;
import java.util.List;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.jfinal.aop.Before;
import com.jfinal.core.Controller;
import com.jfinal.kit.HttpKit;
import com.jfinal.plugin.activerecord.Record;
import com.jfinal.plugin.jwttoken.JwtTokenInterceptor;
import com.wtms.bean.MessageBean;
import com.wtms.common.model.Role;
import com.wtms.common.model.User;
import com.wtms.service.RoleService;
import com.wtms.service.UserService;

/**
 * @author guoce
 *
 */
public class UserController extends Controller{
	static UserService userService = new UserService();
	static RoleService roleService = new RoleService();
	@Before(JwtTokenInterceptor.class)
	public void total() {
		Integer totalCount = userService.total();
		HashMap<String,Integer> data= new HashMap<String,Integer>();
		data.put("total", totalCount);
		renderJson(new MessageBean().setCode(1).setMessage("人员管理_查询全部").setData(data));
	}
	
	public void index() {
		Integer page = getParaToInt("_page");
		Integer limit = getParaToInt("_limit");
		List<Record> users = userService.findAll(page,limit).getList();
		renderJson(new MessageBean().setCode(1).setMessage("人员管理_查询全部").setData(users));
	}
	public void query() {
		Integer userId = getParaToInt(0);
		Record user = userService.findUserInfoById(userId);
		List<Role> roles = roleService.getRolesByUserId(userId);
		user.set("roles", roles);
		renderJson(new MessageBean().setCode(1).setMessage("人员管理_查询单条").setData(user));
	}
	
	public void create() {
		JSONObject para = JSON.parseObject(HttpKit.readData(getRequest()));
		User user = new User().setUsername(para.getString("username")).setPwd(para.getString("pwd")).setName(para.getString("username"))
		.setWorknum(para.getString("worknum")).setSex(para.getString("sex")).setDepartmentId(para.getString("departmentId"))
		.setPositionId(para.getString("positionId"));
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
		.setWorknum(para.getString("worknum")).setSex(para.getString("sex")).setDepartmentId(para.getString("departmentId"))
		.setPositionId(para.getString("positionId")).update();
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
