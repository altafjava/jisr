package com.jisr.exception;

import java.time.LocalDateTime;
import java.util.Map;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ErrorResponse {
	private LocalDateTime timestamp;
	private int status;
	private String error;
	private String message;
	private Map<String, String> details;
	private String stackTrace;

	public ErrorResponse(int status, String error, String message, Map<String, String> details, String stackTrace) {
		this.timestamp = LocalDateTime.now();
		this.status = status;
		this.error = error;
		this.message = message;
		this.details = details;
		this.stackTrace = stackTrace;
	}
}
