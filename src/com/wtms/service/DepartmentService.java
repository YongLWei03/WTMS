package com.wtms.service;

import java.util.List;

import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Page;
import com.jfinal.plugin.activerecord.Record;
import com.wtms.common.model.Department;

public class DepartmentService{
	private static final Department dao = new Department().dao();
	
	public Integer total(){
		Integer totalCount = Db.queryInt("select count(*) from wf_department");
		return totalCount;
	}
	public Page<Department> findAll(int page,int limit){
		Page<Department> departments = dao.paginate(page, limit,"select * "," from wf_department");
		return departments;
	}
	
	public List<Department> query(){
		return dao.find("select * from wf_department wd");
	}
	public List<Record> queryByParentId(Integer parentId) {
		return Db.find("select * from wf_department wd where wd.parentid = "+parentId);
	}
	public int countChildByParentId(int id) {
		Integer childCount = Db.queryInt("select count(*) from wf_department where parentid = "+id);
		return childCount;
	}
}
