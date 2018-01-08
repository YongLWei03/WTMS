package com.wtms.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.jfinal.core.Controller;
import com.jfinal.kit.HttpKit;
import com.jfinal.plugin.activerecord.Record;
import com.wtms.bean.MessageBean;
import com.wtms.common.model.Department;
import com.wtms.common.model.Fault;
import com.wtms.common.model.Flevel;
import com.wtms.common.model.Kks;
import com.wtms.common.model.User;
import com.wtms.common.model.Fault;
import com.wtms.service.FaultService;
import com.wtms.service.KksService;

public class FaultController extends Controller{
	static FaultService faultService = new FaultService();
	static KksService kksService = new KksService();
	static UserController userController = new UserController();
	static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	
	public void total() {
		Integer totalCount = faultService.total();
		HashMap<String,Integer> data= new HashMap<String,Integer>();
		data.put("total", totalCount);
		renderJson(new MessageBean().setCode(1).setMessage("缺陷管理_查询全部").setData(data));
	}
	
	public void index() {
		Integer page = getParaToInt("_page");
		Integer limit = getParaToInt("_limit");
		List<Record> faults = faultService.findAll(page,limit).getList();
		for (Record record : faults) {
			Record user = userController.detailById(record.getInt("userId"));
			record.set("user", user);
			Flevel flevel = faultService.findById(record.getInt("flevelId"));
			record.set("flevel", flevel);
			Kks kks = kksService.findById(record.getInt("kksId"));
			record.set("kks", kks);
		}
		renderJson(new MessageBean().setCode(1).setMessage("缺陷管理_查询全部").setData(faults));
	}
	
	public void create() throws ParseException {
		JSONObject para = JSON.parseObject(HttpKit.readData(getRequest()));
		
		Fault fault = new Fault().setFnumber(System.currentTimeMillis()+"").setFstate(para.getInteger("fstate")).setFlevelId(para.getInteger("flevelId")).setUserId(para.getInteger("userId"))
		.setTjDate(sdf.parse(para.getString("tjDate"))).setYqjsDate(sdf.parse(para.getString("yqjsDate"))).setDesc(para.getString("desc"))
		.setGroupIds(para.getString("groupIds")).setTeamIds(para.getString("teamIds")).setPhone(para.getString("phone")).setComments(para.getString("comments"));
		Boolean isOk= fault.save();
		int faultId = fault.getId();
		renderJson(new MessageBean().setCode(1).setMessage("缺陷管理_新增").setData(isOk));
	}
	
	public void update() throws ParseException {
		JSONObject para = JSON.parseObject(HttpKit.readData(getRequest()));
		Integer faultId = para.getInteger("id");
		new Fault().setId(faultId).setFstate(para.getInteger("fstate")).setFlevelId(para.getInteger("flevelId")).setUserId(para.getInteger("userId"))
		.setTjDate(sdf.parse(para.getString("tjDate"))).setYqjsDate(sdf.parse(para.getString("yqjsDate"))).setDesc(para.getString("desc"))
		.setGroupIds(para.getString("groupIds")).setTeamIds(para.getString("teamIds")).setPhone(para.getString("phone")).setComments(para.getString("comments"))
		.update();

		renderJson(new MessageBean().setCode(1).setMessage("缺陷管理_更新"));
	}
	
	/**
	 * 删除用户，先删除各种关系 userRole userMenu 
	 */
	public void delete() {
		Integer faultId = getParaToInt(0);
		faultService.deleteById(faultId);
		new Fault().setId(faultId).delete();
		renderJson(new MessageBean().setCode(1).setMessage("缺陷管理_删除"));
	}
	
}
