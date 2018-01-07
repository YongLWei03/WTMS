package com.wtms.controller;

import java.util.HashMap;
import java.util.List;

import com.jfinal.core.Controller;
import com.wtms.bean.MessageBean;
import com.wtms.common.model.Workticketstate;
import com.wtms.service.WorkTicketStateService;

public class WorkTicketStateController extends Controller{
	static WorkTicketStateService workTicketStateService = new WorkTicketStateService();
	public void total() {
		Integer totalCount = workTicketStateService.total();
		HashMap<String,Integer> data= new HashMap<String,Integer>();
		data.put("total", totalCount);
		renderJson(new MessageBean().setCode(1).setMessage("缺陷状态管理_查询全部").setData(data));
	}
	
	public void index() {
		Integer page = getParaToInt("_page");
		Integer limit = getParaToInt("_limit");
		List<Workticketstate> positions = workTicketStateService.findAll(page,limit).getList();
		renderJson(new MessageBean().setCode(1).setMessage("缺陷状态管理_查询全部").setData(positions));
	}
	
	public void query() {
		List<Workticketstate> positions = workTicketStateService.query();
		renderJson(new MessageBean().setCode(1).setMessage("缺陷状态管理_查询全部").setData(positions));
	}
}
