package com.jisr.exception;

import java.util.HashMap;
import java.util.Map;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authorization.AuthorizationDeniedException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.UnsupportedJwtException;
import io.jsonwebtoken.security.SignatureException;

@RestControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(DataIntegrityViolationException.class)
	public ResponseEntity<ErrorResponse> handleDataIntegrityViolationException(DataIntegrityViolationException ex) {
		String message = "Database error. Please check the provided data.";
		Map<String, String> details = new HashMap<>();
		Throwable cause = ex.getCause();
		if (cause != null && cause instanceof org.hibernate.exception.ConstraintViolationException) {
			org.hibernate.exception.ConstraintViolationException cve = (org.hibernate.exception.ConstraintViolationException) cause;
			extractExceptionMessage(cve.getMessage(), details);
		} else if (cause != null && cause instanceof java.sql.SQLException) {
			java.sql.SQLException sqlException = (java.sql.SQLException) cause;
			extractExceptionMessage(sqlException.getMessage(), details);
		}
		ErrorResponse errorResponse = new ErrorResponse(HttpStatus.BAD_REQUEST.value(), HttpStatus.BAD_REQUEST.getReasonPhrase(), message, details, null);
		return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
	}

	private void extractExceptionMessage(String errorMessage, Map<String, String> details) {
		int bracketIndex = errorMessage.indexOf("[");
		String firstPart = (bracketIndex != -1) ? errorMessage.substring(0, bracketIndex).trim() : errorMessage;
		String secondPart = (bracketIndex != -1) ? errorMessage.substring(bracketIndex) : "";
		details.put(firstPart, secondPart);
	}

	@ExceptionHandler(RoleNotFoundException.class)
	public ResponseEntity<ErrorResponse> handleRoleNotFoundException(RoleNotFoundException ex) {
		ErrorResponse errorResponse = new ErrorResponse(HttpStatus.NOT_FOUND.value(), HttpStatus.NOT_FOUND.getReasonPhrase(), ex.getMessage(), null, null);
		return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(IllegalArgumentException.class)
	public ResponseEntity<ErrorResponse> handleIllegalArgumentException(IllegalArgumentException ex) {
		ErrorResponse errorResponse = new ErrorResponse(HttpStatus.BAD_REQUEST.value(), HttpStatus.BAD_REQUEST.getReasonPhrase(), ex.getMessage(), null, null);
		return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(BadCredentialsException.class)
	public ResponseEntity<ErrorResponse> handleBadCredentials(BadCredentialsException ex) {
		ErrorResponse errorResponse = new ErrorResponse(HttpStatus.UNAUTHORIZED.value(), HttpStatus.UNAUTHORIZED.getReasonPhrase(),
				"Invalid username or password. Please try again.", null, null);
		return new ResponseEntity<>(errorResponse, HttpStatus.UNAUTHORIZED);
	}

	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<ErrorResponse> handleValidationExceptions(MethodArgumentNotValidException ex) {
		Map<String, String> validationErrors = new HashMap<>();
		for (FieldError error : ex.getBindingResult().getFieldErrors()) {
			validationErrors.put(error.getField(), error.getDefaultMessage());
		}
		ErrorResponse errorResponse = new ErrorResponse(HttpStatus.BAD_REQUEST.value(), HttpStatus.BAD_REQUEST.getReasonPhrase(),
				"Validation failed for one or more fields", validationErrors, null);
		return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(RegistrationDisabledException.class)
	public ResponseEntity<ErrorResponse> handleRegistrationDisabledException(RegistrationDisabledException ex) {
		ErrorResponse errorResponse = new ErrorResponse(HttpStatus.SERVICE_UNAVAILABLE.value(), HttpStatus.SERVICE_UNAVAILABLE.getReasonPhrase(),
				ex.getMessage(), null, // Or any additional details you want to include
				null);
		return new ResponseEntity<>(errorResponse, HttpStatus.SERVICE_UNAVAILABLE);
	}

	@ExceptionHandler(HttpMessageNotReadableException.class)
	public ResponseEntity<ErrorResponse> handleHttpMessageNotReadable(HttpMessageNotReadableException ex) {
		String message = ex.getMessage();
		if (message != null && message.contains("LocalDate")) {
			ErrorResponse errorResponse = new ErrorResponse(HttpStatus.BAD_REQUEST.value(), HttpStatus.BAD_REQUEST.getReasonPhrase(),
					"Invalid date format. Expected format: dd-MM-yyyy", null, null);
			return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
		}
		// Generic error for other deserialization issues
		ErrorResponse errorResponse = new ErrorResponse(HttpStatus.BAD_REQUEST.value(), HttpStatus.BAD_REQUEST.getReasonPhrase(), "Invalid request payload",
				null, ExceptionUtils.getStackTrace(ex));
		return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(TokenNotValidException.class)
	public ResponseEntity<ErrorResponse> handleTokenNotValidException(TokenNotValidException ex) {
		ErrorResponse errorResponse = new ErrorResponse(HttpStatus.BAD_REQUEST.value(), HttpStatus.BAD_REQUEST.getReasonPhrase(), ex.getMessage(), null, null);
		return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler({ ExpiredJwtException.class, MalformedJwtException.class, SignatureException.class, UnsupportedJwtException.class })
	public ResponseEntity<ErrorResponse> handleJwtExceptions(RuntimeException ex) {
		String message;
		if (ex instanceof ExpiredJwtException) {
			message = "JWT token has expired. Please log in again.";
		} else if (ex instanceof MalformedJwtException) {
			message = "JWT token is malformed. Please check the token format.";
		} else if (ex instanceof SignatureException) {
			message = "JWT token signature is invalid.";
		} else if (ex instanceof UnsupportedJwtException) {
			message = "JWT token format is not supported.";
		} else {
			message = "JWT token processing error.";
		}
		ErrorResponse errorResponse = new ErrorResponse(HttpStatus.UNAUTHORIZED.value(), HttpStatus.UNAUTHORIZED.getReasonPhrase(), message, null, null);
		return new ResponseEntity<>(errorResponse, HttpStatus.UNAUTHORIZED);
	}

	@ExceptionHandler(AuthorizationDeniedException.class)
	public ResponseEntity<ErrorResponse> handleAuthorizationDeniedException(AuthorizationDeniedException ex) {
		ErrorResponse errorResponse = new ErrorResponse(HttpStatus.FORBIDDEN.value(), HttpStatus.FORBIDDEN.getReasonPhrase(),
				"You do not have permission to access this resource.", null, null);
		return new ResponseEntity<>(errorResponse, HttpStatus.FORBIDDEN);
	}

	@ExceptionHandler(Exception.class)
	public ResponseEntity<ErrorResponse> handleGenericException(Exception ex) {
		ErrorResponse errorResponse = new ErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR.value(), HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase(),
				"An unexpected error occurred. Please try again later.", null, ExceptionUtils.getStackTrace(ex));
		return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
	}

}
