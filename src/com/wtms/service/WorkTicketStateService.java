package com.wtms.service;

import java.util.List;

import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Page;
import com.wtms.common.model.Workticketstate;

public class WorkTicketStateService{
	private static final Workticketstate dao = new Workticketstate().dao();
	
	public Integer total(){
		Integer totalCount = Db.queryInt("select count(*) from wf_workticketstate");
		return totalCount;
	}
	public Page<Workticketstate> findAll(int page,int limit){
		Page<Workticketstate> positions = dao.paginate(page, limit,"select id,name "," from wf_workticketstate");
		return positions;
	}
	
	public List<Workticketstate> query(){
		return dao.find("select wd.id,wd.name from wf_workticketstate wd");
	}
}
