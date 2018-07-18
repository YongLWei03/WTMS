package com.wtms.service;

import java.util.List;
import java.util.Map;

import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Page;
import com.jfinal.plugin.activerecord.Record;
import com.wtms.bean.StandardOperateTicketHeadBean;
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
		return (otid==null||otid=="")?"0":otid;
	}
	
	public List<Record> buildEquipmentsNode(String segment){
		return Db.find("SELECT DISTINCT ww.task,ww.switchName, ww.switchNum from wf_operatecontent ww where ww.segment ='"+segment+"'");
	}
	
	//删除操作票典型票
	public int deleteOperateContentByMap(StandardOperateTicketHeadBean headBean) {
		return Db.delete("delete from wf_operatecontent where segment = '" + headBean.getSegment()
				+ "'  and TicketNum = '" + headBean.getTicketNum() + "' and task = '" + headBean.getTask() + "'");

		// 要同步删除危控点关联

	}
	//保持导入的操作票典型票
	public boolean saveOperateContentByMap(StandardOperateTicketHeadBean headBean, Map m) {
		String otid = (String) m.get("otid");
		Integer optitemid = Integer.parseInt((String) m.get("序号*"));
		String dangerPointIds = m.get("操作内容*").toString();
		String optItemContent;// = m.get("关联危控号").toString();
		if (m.get("关联危控号") == null) {
			optItemContent = "";
		} else {
			optItemContent = m.get("关联危控号").toString();
		}
		
		return new Operatecontent().setSegment(headBean.getSegment()).setFactory(headBean.getFactory())
				.setTicketNum(headBean.getTicketNum()).setTicketType(headBean.getTicketType())
				.setUnit(headBean.getUnit()).setSet(headBean.getSet()).setProfession(headBean.getProfession())
				.setTask(headBean.getTask()).setSwitchName(headBean.getSwitchName())
				.setSwitchNum(headBean.getSwitchNum()).setQRcode(headBean.getQRcode()).setSort(headBean.getSort())
				.setEditor(headBean.getEditor()).setEditDate(headBean.getEditDate()).setReviewer(headBean.getReviewer())
				.setReviewDate(headBean.getReviewDate()).setApprover(headBean.getApprover())
				.setApproveDate(headBean.getApproveDate()).setOperateTicketId(otid).setOptitemid(optitemid)
				.setDangerPointIds(dangerPointIds).setOptItemContent(optItemContent).save();
	}

	
}
