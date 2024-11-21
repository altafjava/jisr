package com.jisr.validator;

import com.jisr.constant.ValidGender;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class GenderValidator implements ConstraintValidator<ValidGender, String> {

	@Override
	public boolean isValid(String value, ConstraintValidatorContext context) {
		if (value == null || value.isEmpty()) {
			return true; // Allow null or empty as it could be optional in PATCH API
		}
		try {
			// Validate gender by trying to map it to an enum
			Gender.fromString(value);
			return true;
		} catch (IllegalArgumentException e) {
			return false; // Invalid gender
		}
	}
}
