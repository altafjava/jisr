package com.jisr.exception;

public class TokenNotValidException extends RuntimeException {

	private static final long serialVersionUID = -6188219571847445926L;

	public TokenNotValidException(String message) {
		super(message);
	}
}
