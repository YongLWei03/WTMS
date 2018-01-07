package com.wtms.common.model.base;

import com.jfinal.plugin.activerecord.Model;
import com.jfinal.plugin.activerecord.IBean;

/**
 * Generated by JFinal, do not modify this file.
 */
@SuppressWarnings({"serial", "unchecked"})
public abstract class BaseFault<M extends BaseFault<M>> extends Model<M> implements IBean {

	public M setId(java.lang.Integer id) {
		set("id", id);
		return (M)this;
	}
	
	public java.lang.Integer getId() {
		return getInt("id");
	}

	public M setFnumber(java.lang.String fnumber) {
		set("fnumber", fnumber);
		return (M)this;
	}
	
	public java.lang.String getFnumber() {
		return getStr("fnumber");
	}

	public M setFstate(java.lang.Integer fstate) {
		set("fstate", fstate);
		return (M)this;
	}
	
	public java.lang.Integer getFstate() {
		return getInt("fstate");
	}

	public M setFlevelId(java.lang.Integer flevelId) {
		set("flevelId", flevelId);
		return (M)this;
	}
	
	public java.lang.Integer getFlevelId() {
		return getInt("flevelId");
	}

	public M setUserId(java.lang.Integer userId) {
		set("userId", userId);
		return (M)this;
	}
	
	public java.lang.Integer getUserId() {
		return getInt("userId");
	}

	public M setGroupIds(java.lang.String groupIds) {
		set("groupIds", groupIds);
		return (M)this;
	}
	
	public java.lang.String getGroupIds() {
		return getStr("groupIds");
	}

	public M setTeamIds(java.lang.String teamIds) {
		set("teamIds", teamIds);
		return (M)this;
	}
	
	public java.lang.String getTeamIds() {
		return getStr("teamIds");
	}

	public M setPhone(java.lang.String phone) {
		set("phone", phone);
		return (M)this;
	}
	
	public java.lang.String getPhone() {
		return getStr("phone");
	}

	public M setDesc(java.lang.String desc) {
		set("desc", desc);
		return (M)this;
	}
	
	public java.lang.String getDesc() {
		return getStr("desc");
	}

	public M setComments(java.lang.String comments) {
		set("comments", comments);
		return (M)this;
	}
	
	public java.lang.String getComments() {
		return getStr("comments");
	}

	public M setTjDate(java.util.Date tjDate) {
		set("tjDate", tjDate);
		return (M)this;
	}
	
	public java.util.Date getTjDate() {
		return get("tjDate");
	}

	public M setYqjsDate(java.util.Date yqjsDate) {
		set("yqjsDate", yqjsDate);
		return (M)this;
	}
	
	public java.util.Date getYqjsDate() {
		return get("yqjsDate");
	}

	public M setCreateTime(java.util.Date createTime) {
		set("createTime", createTime);
		return (M)this;
	}
	
	public java.util.Date getCreateTime() {
		return get("createTime");
	}

}
