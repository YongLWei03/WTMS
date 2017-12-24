package com.wtms.bean;

import java.util.List;

public class MenuBean {
	protected Integer id;
	protected String name;
	protected List<ChildBean> children;
	
	public Integer getId() {
		return id;
	}
	public MenuBean setId(Integer id) {
		this.id = id;
		return(this);
	}
	public String getName() {
		return name;
	}
	public MenuBean setName(String name) {
		this.name = name;
		return(this);
	}
	public List<ChildBean> getChildren() {
		return children;
	}
	public MenuBean setChildren(List<ChildBean> children) {
		this.children = children;
		return(this);
	}
	
	
	
}
