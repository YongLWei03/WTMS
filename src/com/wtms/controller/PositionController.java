package com.wtms.controller;

import java.util.HashMap;
import java.util.List;

import com.jfinal.core.Controller;
import com.wtms.bean.MessageBean;
import com.wtms.common.model.Department;
import com.wtms.common.model.Position;
import com.wtms.service.PositionService;

public class PositionController extends Controller{
	static PositionService positionService = new PositionService();
//	@Before(JwtTokenInterceptor.class)
	public void total() {
		Integer totalCount = positionService.total();
		HashMap<String,Integer> data= new HashMap<String,Integer>();
		data.put("total", totalCount);
		renderJson(new MessageBean().setCode(1).setMessage("岗位管理_查询全部").setData(data));
	}
	
	public void index() {
		Integer page = getParaToInt("_page");
		Integer limit = getParaToInt("_limit");
		List<Position> positions = positionService.findAll(page,limit).getList();
		renderJson(new MessageBean().setCode(1).setMessage("岗位管理_查询全部").setData(positions));
	}
	
	public void query() {
		List<Position> positions = positionService.query();
		renderJson(new MessageBean().setCode(1).setMessage("岗位管理_查询全部").setData(positions));
	}
}
