package com.wtms.service;

import java.util.List;
import java.util.Map;

import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Page;
import com.wtms.common.model.Operatecontent;

public class OperateTicketService{
	private static final Operatecontent dao = new Operatecontent().dao();
	
	public Integer total(){
		Integer totalCount = Db.queryInt("select count(*) from wf_operatecontent");
		return totalCount;
	}
	public List<Operatecontent> findAll(){
		List<Operatecontent> operateContents = dao.find("select * from wf_operatecontent");
		return operateContents;
	}
	public Page<Operatecontent> findAll(int page,int limit){
		Page<Operatecontent> operateContents = dao.paginate(page, limit,"select id,name "," from wf_operatecontent");
		return operateContents;
	}
	public Operatecontent getOperateContentById(Integer id) {
		return dao.findById(id);
	}
	public List<Operatecontent> getBrolesByTicketId(String ticketId) {
		return dao.find("select wd.* from wf_operatecontent wd where wd.id ="+ticketId);
	}
	public String getMaxOperateTicketId(){
		String otid = Db.queryStr("select substr(wf.operateTicketId,3) otid from wf_operatecontent wf order by wf.id desc limit 1");
		return (otid==null||otid=="")?"1":otid;
	}
	
	public boolean saveOperateContentByMap(Map m) {
		Integer otid = (Integer) m.get("otid");
		Integer optitemid = Integer.parseInt((String) m.get("序号*"));
		String dangerPointIds = m.get("操作内容*").toString();
		String optItemContent ;//= m.get("关联危控号").toString();
		if(m.get("关联危控号")==null){
			optItemContent = "";
		}else{
			optItemContent = m.get("关联危控号").toString();
		}
		
		return new Operatecontent().setOperateTicketId(otid).setOptitemid(optitemid)
				.setDangerPointIds(dangerPointIds).setOptItemContent(optItemContent).save();
	}
	
}
