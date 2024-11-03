package com.jisr.exception;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ApiError {
	private int errorCode;
	private String errorMessage;
	private String errorDetails;
	private long timestamp;

	public ApiError(int errorCode, String errorMessage, String errorDetails, long timestamp) {
		this.errorCode = errorCode;
		this.errorMessage = errorMessage;
		this.errorDetails = errorDetails;
		this.timestamp = timestamp;
	}

}
