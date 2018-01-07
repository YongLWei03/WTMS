package com.wtms.controller;

import java.util.List;

import com.jfinal.core.Controller;
import com.wtms.bean.MessageBean;
import com.wtms.common.model.Flevel;
import com.wtms.service.FaultService;

public class FlevelController extends Controller{
	static FaultService faultService = new FaultService();
	
	public void query() {
		List<Flevel> departments = faultService.queryFlever();
		renderJson(new MessageBean().setCode(1).setMessage("缺陷级别_查询全部").setData(departments));
	}
	
}
