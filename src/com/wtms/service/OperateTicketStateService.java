package com.wtms.service;

import java.util.List;

import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Page;
import com.wtms.common.model.Operateticketstate;

public class OperateTicketStateService{
	private static final Operateticketstate dao = new Operateticketstate().dao();
	
	public Integer total(){
		Integer totalCount = Db.queryInt("select count(*) from wf_operateticketstate");
		return totalCount;
	}
	public Page<Operateticketstate> findAll(int page,int limit){
		Page<Operateticketstate> positions = dao.paginate(page, limit,"select id,name "," from wf_operateticketstate");
		return positions;
	}
	
	public List<Operateticketstate> query(){
		return dao.find("select wd.id,wd.name from wf_operateticketstate wd");
	}
}
