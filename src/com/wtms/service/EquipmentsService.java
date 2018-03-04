package com.wtms.service;

import java.util.List;

import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Page;
import com.jfinal.plugin.activerecord.Record;
import com.wtms.common.model.Equipments;

public class EquipmentsService{
	private static final Equipments dao = new Equipments().dao();
	
	public Integer total(){
		Integer totalCount = Db.queryInt("select count(*) from wf_equipments");
		return totalCount;
	}
	public Page<Equipments> findAll(int page,int limit){
		Page<Equipments> departments = dao.paginate(page, limit,"select * "," from wf_equipments");
		return departments;
	}
	
	public List<Equipments> query(){
		return dao.find("select * from wf_equipments wd");
	}
	public List<Record> queryByParentId(Integer parentId) {
		return Db.find("select * from wf_equipments wd where wd.parentid = "+parentId);
	}
	public int countChildByParentId(int id) {
		Integer childCount = Db.queryInt("select count(*) from wf_equipments where parentid = "+id);
		return childCount;
	}
}
