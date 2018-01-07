package com.wtms.service;

import java.util.List;

import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Page;
import com.jfinal.plugin.activerecord.Record;
import com.wtms.common.model.Brole;
import com.wtms.common.model.User;

public class BroleService{
	private static final Brole dao = new Brole().dao();
	
	public Integer total(){
		Integer totalCount = Db.queryInt("select count(*) from wf_brole");
		return totalCount;
	}
	public List<Brole> findAll(){
		List<Brole> boles = dao.find("select * from wf_brole");
		return boles;
	}
	public Page<Brole> findAll(int page,int limit){
		Page<Brole> broles = dao.paginate(page, limit,"select id,name "," from wf_brole");
		return broles;
	}
	public List<Brole> getBrolesByUserId(Integer userId) {
		return dao.find("select wd.id,wd.name from wf_brole wd");
	}
	public List<Brole> getBrolesByBroleIds(String broleIds) {
		return dao.find("select wd.id,wd.name from wf_brole wd where wd.id in ("+broleIds+")");
	}
	
}
