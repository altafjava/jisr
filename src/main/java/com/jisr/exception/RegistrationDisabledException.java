package com.jisr.exception;

public class RegistrationDisabledException extends RuntimeException {
	
	private static final long serialVersionUID = 3902254531145501877L;

	public RegistrationDisabledException(String message) {
		super(message);
	}
}
