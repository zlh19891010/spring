package com.admin.server.exception.impl;

import com.admin.server.exception.ValidationException;


public class DataValidationException extends ValidationException {

	public DataValidationException() {
		super();
	}

	public DataValidationException(String message, Throwable cause) {
		super(message, cause);
	}

	public DataValidationException(String message) {
		super(message);
	}

	public DataValidationException(Throwable cause) {
		super(cause);
	}

}
