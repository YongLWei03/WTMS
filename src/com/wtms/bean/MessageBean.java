package com.wtms.bean;

public class MessageBean {
	protected Integer code;
	protected String message;
	protected Object data;
	public Integer getCode() {
		return code;
	}
	public MessageBean setCode(Integer code) {
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
	public Object getData() {
		return data;
	}
	public MessageBean setData(Object data) {
		this.data = data;
		return(this);
	}
}
