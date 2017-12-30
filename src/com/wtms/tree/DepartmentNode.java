package com.wtms.tree;

import java.util.List;

public class DepartmentNode {
	private int id;
	private String name;
	private int parentId;
	private List<DepartmentNode> childList;
	private String path;  //当前department的路径
	public int getId() {
		return id;
	}
	public DepartmentNode setId(int id) {
		this.id = id;
		return(this);
	}
	public String getName() {
		return name;
	}
	public DepartmentNode setName(String name) {
		this.name = name;
		return(this);
	}
	public int getParentId() {
		return parentId;
	}
	public DepartmentNode setParentId(int parentId) {
		this.parentId = parentId;
		return(this);
	}
	public List<DepartmentNode> getChildList() {
		return childList;
	}
	public DepartmentNode setChildList(List<DepartmentNode> childList) {
		this.childList = childList;
		return(this);
	}
	public String getPath() {
		return path;
	}
	public DepartmentNode setPath(String path) {
		this.path = path;
		return(this);
	}
}
