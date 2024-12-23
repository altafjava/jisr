package com.jisr.exception;

public class RoleNotFoundException extends RuntimeException {
	
	private static final long serialVersionUID = -3331732894540098696L;

	public RoleNotFoundException(String message) {
		super(message);
	}
}
