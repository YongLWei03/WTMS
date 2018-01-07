package com.wtms.service;

import java.util.List;

import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Page;
import com.wtms.common.model.Department;
import com.wtms.common.model.Fstate;
import com.wtms.common.model.User;

public class FstateService{
	private static final Fstate dao = new Fstate().dao();
	
	public Integer total(){
		Integer totalCount = Db.queryInt("select count(*) from wf_fstate");
		return totalCount;
	}
	public Page<Fstate> findAll(int page,int limit){
		Page<Fstate> positions = dao.paginate(page, limit,"select id,name "," from wf_fstate");
		return positions;
	}
	
	public List<Fstate> query(){
		return dao.find("select wd.id,wd.name from wf_fstate wd");
	}
}
