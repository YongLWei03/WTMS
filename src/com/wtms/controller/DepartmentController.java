package com.wtms.controller;

import java.util.HashMap;
import java.util.List;

import com.jfinal.core.Controller;
import com.wtms.bean.MessageBean;
import com.wtms.common.model.Department;
import com.wtms.common.model.Position;
import com.wtms.service.DepartmentService;

public class DepartmentController extends Controller{
	static DepartmentService departmentService = new DepartmentService();
//	@Before(JwtTokenInterceptor.class)
	public void total() {
		Integer totalCount = departmentService.total();
		HashMap<String,Integer> data= new HashMap<String,Integer>();
		data.put("total", totalCount);
		renderJson(new MessageBean().setCode(1).setMessage("部门管理_查询全部").setData(data));
	}
	
	public void index() {
		Integer page = getParaToInt("_page");
		Integer limit = getParaToInt("_limit");
		List<Department> departments = departmentService.findAll(page,limit).getList();
		renderJson(new MessageBean().setCode(1).setMessage("部门管理_查询全部").setData(departments));
	}
	
	public void query() {
		List<Department> departments = departmentService.query();
		renderJson(new MessageBean().setCode(1).setMessage("部门管理_查询全部").setData(departments));
	}
}
