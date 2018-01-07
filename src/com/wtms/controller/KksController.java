package com.wtms.controller;

import java.util.HashMap;
import java.util.List;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.jfinal.core.Controller;
import com.jfinal.kit.HttpKit;
import com.jfinal.plugin.activerecord.Record;
import com.wtms.bean.MessageBean;
import com.wtms.common.model.Kks;
import com.wtms.common.model.User;
import com.wtms.service.KksService;

public class KksController extends Controller{
	static KksService kksService = new KksService();
	public void total() {
		Integer totalCount = kksService.total();
		HashMap<String,Integer> data= new HashMap<String,Integer>();
		data.put("total", totalCount);
		renderJson(new MessageBean().setCode(1).setMessage("KKS管理_查询全部").setData(data));
	}
	
	public void index() {
		Integer page = getParaToInt("_page");
		Integer limit = getParaToInt("_limit");
		List<Kks> kks = kksService.findAll(page,limit).getList();
		renderJson(new MessageBean().setCode(1).setMessage("KKS管理_查询全部").setData(kks));
	}
	
	public void query() {
		int parentId = getParaToInt("parentId");
		List<Record> kks = kksService.queryByParentId(parentId);
		for (Record record : kks) {
			int id = record.get("id");
			int countChild = kksService.countChildByParentId(id);
			if(countChild > 0){
				record.set("hasChildren",true);
			}
		}
		renderJson(new MessageBean().setCode(1).setMessage("KKS管理_查询全部").setData(kks));
	}
	
	public void create() {
		JSONObject para = JSON.parseObject(HttpKit.readData(getRequest()));
		Integer parentId = para.getInteger("parentId");
		String name = para.getString("name");
		String desc = para.getString("desc");
		String sid = para.getString("sid");
		Kks kks = new Kks().setParentId(parentId).setName(name).setDesc(desc).setSid(sid);
		Boolean isOk= kks.save();
		Integer userid = kks.getId();
		renderJson(new MessageBean().setCode(1).setMessage("KKS管理_新增").setData(isOk));
	}
	
	public void update() {
		Integer kksId = getParaToInt(0);
		JSONObject para = JSON.parseObject(HttpKit.readData(getRequest()));
		Integer parentId = para.getInteger("parentId");
		String name = para.getString("name");
		String desc = para.getString("desc");
		String sid = para.getString("sid");
		new Kks().setParentId(parentId).setName(name).setDesc(desc).setSid(sid).setId(kksId).update();
		renderJson(new MessageBean().setCode(1).setMessage("KKS管理_更新一条"));
	}
	
	public void delete() {
		Integer kksId = getParaToInt(0);
		kksService.deleteByKksId(kksId);
		renderJson(new MessageBean().setCode(1).setMessage("KKS管理_删除一条"));
	}
}
