package com.wtms.controller;

import java.util.HashMap;
import java.util.List;

import com.jfinal.core.Controller;
import com.wtms.bean.MessageBean;
import com.wtms.common.model.Brole;
import com.wtms.common.model.Position;
import com.wtms.service.BroleService;

/**
 * @author guoce
 * 业务角色管理
 */
public class BroleController extends Controller{
	static BroleService brolesService = new BroleService();
//	@Before(JwtTokenInterceptor.class)
	public void total() {
		Integer totalCount = brolesService.total();
		HashMap<String,Integer> data= new HashMap<String,Integer>();
		data.put("total", totalCount);
		renderJson(new MessageBean().setCode(1).setMessage("业务角色_查询全部").setData(data));
	}
	
	public void index() {
		Integer page = getParaToInt("_page");
		Integer limit = getParaToInt("_limit");
		List<Brole> Broles = brolesService.findAll(page,limit).getList();
		renderJson(new MessageBean().setCode(1).setMessage("业务角色_查询全部").setData(Broles));
	}
}
