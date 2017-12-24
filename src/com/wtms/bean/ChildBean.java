package com.wtms.bean;

public class ChildBean{
	protected Integer id;
	protected Integer menuId;
	protected String name;
	
	public Integer getId() {
		return id;
	}
	public ChildBean setId(Integer id) {
		this.id = id;
		return(this);
	}
	public Integer getMenuId() {
		return menuId;
	}
	public ChildBean setMenuId(Integer menuId) {
		this.menuId = menuId;
		return(this);
	}
	public String getName() {
		return name;
	}
	public ChildBean setName(String name) {
		this.name = name;
		return(this);
	}
}
