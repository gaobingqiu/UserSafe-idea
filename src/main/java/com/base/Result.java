package com.base;

public class Result {
	private String message;
	boolean isSuccess;
	public Result(String message,boolean isSuccess) {
		// TODO Auto-generated constructor stub
		this.isSuccess = isSuccess;
		this.message = message;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public boolean isSuccess() {
		return isSuccess;
	}
	public void setSuccess(boolean isSuccess) {
		this.isSuccess = isSuccess;
	}

}
