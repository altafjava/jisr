package com.jisr.exception;

public class RetryableException extends RuntimeException {

	private static final long serialVersionUID = 4522218154668196731L;

	public RetryableException(String message) {
		super(message);
	}

	public RetryableException(Exception exception) {
		super(exception);
	}
}
