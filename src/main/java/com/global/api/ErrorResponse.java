package com.global.api;
 
public class ErrorResponse {
	private String resourceKey;
	private String errorCode;
	private String message;
	public ErrorResponse(String resourceKey,String errorCode,String message) {
		this.resourceKey = resourceKey;
		this.errorCode = errorCode;
		this.message = message;
	}
	public String getResourceKey() {
		return resourceKey;
	}
	public void setResourceKey(String resourceKey) {
		this.resourceKey = resourceKey;
	}
	public String getErrorCode() {
		return errorCode;
	}
	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
}
