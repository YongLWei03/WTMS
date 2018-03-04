package com.wtms.controller;

import java.util.HashMap;
import java.util.List;

import com.jfinal.core.Controller;
import com.jfinal.plugin.activerecord.Record;
import com.wtms.bean.MessageBean;
import com.wtms.common.model.Equipments;
import com.wtms.service.EquipmentsService;

public class EquipmentsController extends Controller{
	static EquipmentsService departmentService = new EquipmentsService();
//	@Before(JwtTokenInterceptor.class)
	public void total() {
		Integer totalCount = departmentService.total();
		HashMap<String,Integer> data= new HashMap<String,Integer>();
		data.put("total", totalCount);
		renderJson(new MessageBean().setCode(1).setMessage("部门管理_查询全部").setData(data));
	}
	
	public void query() {
		Integer parentId = getParaToInt("parentId");
		if(parentId != null){
			detail();
		}else{
			Integer page = getParaToInt("_page");
			Integer limit = getParaToInt("_limit");
			if(page == null || limit == null){
				List<Equipments> departments = departmentService.query();
				renderJson(new MessageBean().setCode(1).setMessage("部门管理_查询全部").setData(departments));
			}else{
				List<Equipments> departments = departmentService.findAll(page,limit).getList();
				renderJson(new MessageBean().setCode(1).setMessage("部门管理_查询全部").setData(departments));
			}
			
		}
	}
	
	public void detail() {
		Integer parentId = getParaToInt("parentId");
		List<Record> departments = departmentService.queryByParentId(parentId);
		for (Record record : departments) {
			int id = record.get("id");
			int countChild = departmentService.countChildByParentId(id);
			if(countChild > 0){
				record.set("hasChildren",true);
			}
		}
		renderJson(new MessageBean().setCode(1).setMessage("部门管理_查询全部").setData(departments));
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
