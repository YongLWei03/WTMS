package com.wtms.bean;

import com.jfinal.json.Json;

public class MessageBean {
	protected String code;
	protected String message;
	protected Json data;
	public String getCode() {
		return code;
	}
	public MessageBean setCode(String code) {
		this.code = code;
		return(this);
	}
	public String getMessage() {
		return message;
	}
	public MessageBean setMessage(String message) {
		this.message = message;
		return(this);
	}
	public Json getData() {
		return data;
	}
	public MessageBean setData(Json data) {
		this.data = data;
		return(this);
	}
}
