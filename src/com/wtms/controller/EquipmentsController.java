package com.wtms.controller;

import java.text.ParseException;
import java.util.HashMap;
import java.util.List;

import javax.xml.ws.Action;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.jfinal.core.Controller;
import com.jfinal.kit.HttpKit;
import com.jfinal.plugin.activerecord.Record;
import com.wtms.bean.MessageBean;
import com.wtms.common.model.Equipments;
import com.wtms.common.model.Fault;
import com.wtms.common.model.Kks;
import com.wtms.service.EquipmentsService;
import com.wtms.service.OperateTicketService;

public class EquipmentsController extends Controller{
	static EquipmentsService equipmentService = new EquipmentsService();
	static OperateTicketService operateTicketService = new OperateTicketService();
//	@Before(JwtTokenInterceptor.class)
	public void total() {
		Integer totalCount = equipmentService.total();
		HashMap<String,Integer> data= new HashMap<String,Integer>();
		data.put("total", totalCount);
		renderJson(new MessageBean().setCode(1).setMessage("单元管理_查询全部").setData(data));
	}
	public void query() {
		Integer parentId = getParaToInt("parentId");
		if(parentId != null){
			detail();
		}else{
			Integer page = getParaToInt("_page");
			Integer limit = getParaToInt("_limit");
			if(page == null || limit == null){
				List<Equipments> equipments = equipmentService.query();
				renderJson(new MessageBean().setCode(1).setMessage("单元管理_查询全部").setData(equipments));
			}else{
				List<Equipments> equipments = equipmentService.findAll(page,limit).getList();
				renderJson(new MessageBean().setCode(1).setMessage("单元管理_查询全部").setData(equipments));
			}
			
		}
	}
	
	public void detail() {
		Integer parentId = getParaToInt("parentId");
		List<Record> equipments = equipmentService.queryByParentId(parentId);
//		equipments.add(new Equipments().setId(1000).setName("test").setParentid(4).toRecord());
		for (Record record : equipments) {
			int id = record.get("id");
			String name = record.getStr("name");
			int countChild = equipmentService.countChildByParentId(id);
			if(countChild > 0){
				record.set("hasChildren",true);
			}
			List<Record> switchNode = operateTicketService.buildEquipmentsNode(name);
			countChild = switchNode.size();
			if(switchNode!= null && countChild > 0){
				record.set("hasChildren",true);
				for (Record record2 : switchNode) {
					equipments.add(new Equipments().setId(1000).setName("test").setParentid(parentId).toRecord());
				}
			}
		}
		renderJson(new MessageBean().setCode(1).setMessage("单元管理_查询全部").setData(equipments));
	}
	
	public void create(){
		JSONObject para = JSON.parseObject(HttpKit.readData(getRequest()));
		Integer parentId = para.getInteger("parentId");
		String name = para.getString("name");
		Boolean hasChildren = para.getBoolean("hasChildren");
		Equipments equipments = new Equipments().setParentid(parentId).setName(name).setHasChildren(hasChildren);
		Boolean isOk= equipments.save();
		Integer id = equipments.getId();
		renderJson(new MessageBean().setCode(1).setMessage("单元管理_新增").setData(isOk));
	}
	
	public void update() throws ParseException {
		JSONObject para = JSON.parseObject(HttpKit.readData(getRequest()));
		Integer id = para.getInteger("id");
		new Equipments().setId(id).setParentid(para.getInteger("parentId")).setName(para.getString("name")).update();

		renderJson(new MessageBean().setCode(1).setMessage("单元管理_更新"));
	}
	
//	//创建部门架构树
//	public void createDeparmentTree(){
//		List<Equipments> deparments = departmentService.query();
//		
//		List<DepartmentNode> departmentTree = new ArrayList<DepartmentNode>();
//		//取一级部门
//		for (Equipments department : deparments) {
//			if(department.getInt("parentId") == 0){
//				departmentTree.add(new DepartmentNode().setId(department.getInt("id")).setName(department.getStr("name")).setParentId(0).setPath(department.getStr("id")));
//			}
//		}
//		
//		for (DepartmentNode departmentNode : departmentTree) {
//			departmentNode.setChildList(getChild(departmentNode.getId(),deparments));
//		}
//	}
//
//	private List<DepartmentNode> getChild(int id, List<Department> deparments) {
//		// TODO Auto-generated method stub
//		return null;
//	}
}
