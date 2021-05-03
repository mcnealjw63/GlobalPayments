package com.global.controller;

import java.util.Locale;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import com.global.api.ErrorResponse;
import com.global.exception.DataValidationException;


@ControllerAdvice
public class GlobalErrorAdvise {
	@Autowired
	MessageSource messageSource;
	
	@ExceptionHandler(Exception.class)
	@ResponseStatus(HttpStatus.NOT_FOUND)
	public ResponseEntity<ErrorResponse> handleExceptions(Exception ex) {
		ErrorResponse response =  new ErrorResponse("unexpected.exception","ER006",messageSource.getMessage("unexpected.exception", null, Locale.US));
		return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
	}
	@ExceptionHandler(DataValidationException.class)
	@ResponseStatus(HttpStatus.NOT_FOUND)
	public ResponseEntity<ErrorResponse> DataValidationExceptio(DataValidationException ex) {
		return new ResponseEntity<>(ex.getErrorResponse(), HttpStatus.BAD_REQUEST);
	}
}
