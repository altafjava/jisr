package com.jisr.exception;

public class NotRetryableException extends RuntimeException {

	private static final long serialVersionUID = -5440903195801176454L;

	public NotRetryableException(Exception exception) {
		super(exception);
	}
}
