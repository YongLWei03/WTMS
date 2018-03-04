package com.wtms.service;

import java.util.List;

import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Page;
import com.jfinal.plugin.activerecord.Record;
import com.wtms.common.model.Fault;
import com.wtms.common.model.Flevel;

public class FaultService{
	private static final Fault dao = new Fault().dao();
	
	public Integer total(){
		Integer totalCount = Db.queryInt("select count(*) from wf_fault");
		return totalCount;
	}
	public Page<Record> findAll(int page,int limit){
		Page<Record> faults = Db.paginate(page, limit,"select * "," from wf_fault");
		return faults;
	}
	
	public List<Fault> query(){
		return dao.find("select * from wf_fault wd");
	}
	
	public Fault queryFaultById(Integer id){
		return dao.findById(id);
	}

	public List<Flevel> queryFlever(){
		return Flevel.dao.find("select * from wf_flevel fl");
	}
	public boolean deleteById(Integer faultId) {
		return dao.deleteById(faultId);
	}
	public Flevel findById(Integer id) {
		return Flevel.dao.findById(id);
	}
	
}
