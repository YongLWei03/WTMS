package com.wtms.service;

import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Page;
import com.wtms.common.model.Brole;

public class BroleService{
	private static final Brole dao = new Brole().dao();
	
	public Integer total(){
		Integer totalCount = Db.queryInt("select count(*) from wf_brole");
		return totalCount;
	}
	public Page<Brole> findAll(int page,int limit){
		Page<Brole> broles = dao.paginate(page, limit,"select id,name "," from wf_brole");
		return broles;
	}
	
}
