package com.wtms.service;

import java.util.List;

import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Page;
import com.jfinal.plugin.activerecord.Record;
import com.wtms.common.model.Kks;

public class KksService {
	private static final Kks dao = new Kks().dao();
	
	public Integer total(){
		Integer totalCount = Db.queryInt("select count(*) from wf_kks");
		return totalCount;
	}
	public Page<Kks> findAll(int page,int limit){
		Page<Kks> positions = dao.paginate(page, limit,"select * "," from wf_kks");
		return positions;
	}
	
	public List<Kks> query(){
		return dao.find("select * from wf_kks wd");
	}
	public List<Record> queryByParentId(int parentId) {
		return Db.find("select * from wf_kks wd where wd.parentId = "+parentId);
	}
	public int countChildByParentId(int id) {
		Integer childCount = Db.queryInt("select count(*) from wf_kks where parentid = "+id);
		return childCount;
	}
	public void deleteByKksId(Integer kksId) {
		Db.delete("delete from wf_kks where parentid = "+kksId);
		new Kks().setId(kksId).delete();
	}
	public Kks findById(Integer id) {
		return dao.findById(id);
	}
}
