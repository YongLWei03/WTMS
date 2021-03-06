package com.wtms.common.model.base;

import com.jfinal.plugin.activerecord.Model;
import com.jfinal.plugin.activerecord.IBean;

/**
 * Generated by JFinal, do not modify this file.
 */
@SuppressWarnings({"serial", "unchecked"})
public abstract class BaseRole<M extends BaseRole<M>> extends Model<M> implements IBean {

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

	public M setDescription(java.lang.String description) {
		set("description", description);
		return (M)this;
	}
	
	public java.lang.String getDescription() {
		return getStr("description");
	}

	public M setStatus(java.lang.Integer status) {
		set("status", status);
		return (M)this;
	}
	
	public java.lang.Integer getStatus() {
		return getInt("status");
	}

	public M setCreatedAt(java.util.Date createdAt) {
		set("created_at", createdAt);
		return (M)this;
	}
	
	public java.util.Date getCreatedAt() {
		return get("created_at");
	}

	public M setUpdatedAt(java.util.Date updatedAt) {
		set("updated_at", updatedAt);
		return (M)this;
	}
	
	public java.util.Date getUpdatedAt() {
		return get("updated_at");
	}

	public M setDeletedAt(java.util.Date deletedAt) {
		set("deleted_at", deletedAt);
		return (M)this;
	}
	
	public java.util.Date getDeletedAt() {
		return get("deleted_at");
	}

}
