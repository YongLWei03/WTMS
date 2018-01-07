package com.wtms.controller;

import java.util.HashMap;
import java.util.List;

import com.jfinal.core.Controller;
import com.wtms.bean.MessageBean;
import com.wtms.common.model.Department;
import com.wtms.common.model.Fstate;
import com.wtms.common.model.Position;
import com.wtms.service.FstateService;
import com.wtms.service.PositionService;

public class FstateController extends Controller{
	static FstateService fstateService = new FstateService();
	public void total() {
		Integer totalCount = fstateService.total();
		HashMap<String,Integer> data= new HashMap<String,Integer>();
		data.put("total", totalCount);
		renderJson(new MessageBean().setCode(1).setMessage("缺陷状态管理_查询全部").setData(data));
	}
	
	public void index() {
		Integer page = getParaToInt("_page");
		Integer limit = getParaToInt("_limit");
		List<Fstate> positions = fstateService.findAll(page,limit).getList();
		renderJson(new MessageBean().setCode(1).setMessage("缺陷状态管理_查询全部").setData(positions));
	}
	
	public void query() {
		List<Fstate> positions = fstateService.query();
		renderJson(new MessageBean().setCode(1).setMessage("缺陷状态管理_查询全部").setData(positions));
	}
}
