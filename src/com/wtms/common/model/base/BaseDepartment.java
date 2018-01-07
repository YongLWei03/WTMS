package com.wtms.common.model.base;

import com.jfinal.plugin.activerecord.Model;
import com.jfinal.plugin.activerecord.IBean;

/**
 * Generated by JFinal, do not modify this file.
 */
@SuppressWarnings({"serial", "unchecked"})
public abstract class BaseDepartment<M extends BaseDepartment<M>> extends Model<M> implements IBean {

	public M setId(java.lang.Integer id) {
		set("id", id);
		return (M)this;
	}
	
	public java.lang.Integer getId() {
		return getInt("id");
	}

	public M setName(java.lang.String name) {
		set("name", name);
		return (M)this;
	}
	
	public java.lang.String getName() {
		return getStr("name");
	}

	public M setType(java.lang.String type) {
		set("type", type);
		return (M)this;
	}
	
	public java.lang.String getType() {
		return getStr("type");
	}

	public M setParentId(java.lang.Integer parentId) {
		set("parentId", parentId);
		return (M)this;
	}
	
	public java.lang.Integer getParentId() {
		return getInt("parentId");
	}

	public M setHasChildren(java.lang.Boolean hasChildren) {
		set("hasChildren", hasChildren);
		return (M)this;
	}
	
	public java.lang.Boolean getHasChildren() {
		return get("hasChildren");
	}

}
