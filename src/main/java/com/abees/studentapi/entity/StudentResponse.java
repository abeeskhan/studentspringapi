package com.abees.studentapi.entity;

public class StudentResponse {

	private int code;
	private Object data;
	public int getCode() {
		return code;
	}
	public void setCode(int code) {
		this.code = code;
	}
	public Object getData() {
		return data;
	}
	public void setData(Object data) {
		this.data = data;
	}
	public StudentResponse(int code, Object data) {
		super();
		this.code = code;
		this.data = data;
	}
}
