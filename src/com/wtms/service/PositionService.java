package com.wtms.service;

import java.util.List;

import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Page;
import com.wtms.common.model.Position;
import com.wtms.common.model.User;

public class PositionService{
	private static final Position dao = new Position().dao();
	
	public Integer total(){
		Integer totalCount = Db.queryInt("select count(*) from wf_position");
		return totalCount;
	}
	public Page<Position> findAll(int page,int limit){
		Page<Position> positions = dao.paginate(page, limit,"select id,name "," from wf_position");
		return positions;
	}
	
}
