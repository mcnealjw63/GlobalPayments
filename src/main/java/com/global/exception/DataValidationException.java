package com.global.exception;

import com.global.api.ErrorResponse;

public class DataValidationException extends Exception{
	private static final long serialVersionUID = -9126707871295275233L;
	private ErrorResponse errorResponse ;

	public DataValidationException(ErrorResponse errorResponse) {
		this.errorResponse = errorResponse;
	}

	public ErrorResponse getErrorResponse() {
		return errorResponse;
	}

	public void setErrorResponse(ErrorResponse errorResponse) {
		this.errorResponse = errorResponse;
	}
}
