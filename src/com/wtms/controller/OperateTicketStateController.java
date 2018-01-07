package com.wtms.controller;

import java.util.HashMap;
import java.util.List;

import com.jfinal.core.Controller;
import com.wtms.bean.MessageBean;
import com.wtms.common.model.Operateticketstate;
import com.wtms.service.OperateTicketStateService;

public class OperateTicketStateController extends Controller{
	static OperateTicketStateService operateTicketStateService = new OperateTicketStateService();
	public void total() {
		Integer totalCount = operateTicketStateService.total();
		HashMap<String,Integer> data= new HashMap<String,Integer>();
		data.put("total", totalCount);
		renderJson(new MessageBean().setCode(1).setMessage("操作票状态管理_查询全部").setData(data));
	}
	
	public void index() {
		Integer page = getParaToInt("_page");
		Integer limit = getParaToInt("_limit");
		List<Operateticketstate> positions = operateTicketStateService.findAll(page,limit).getList();
		renderJson(new MessageBean().setCode(1).setMessage("操作票状态管理_查询全部").setData(positions));
	}
	
	public void query() {
		List<Operateticketstate> positions = operateTicketStateService.query();
		renderJson(new MessageBean().setCode(1).setMessage("操作票状态管理_查询全部").setData(positions));
	}
}
