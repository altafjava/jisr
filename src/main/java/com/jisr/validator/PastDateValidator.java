package com.jisr.validator;

import java.time.LocalDate;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class PastDateValidator implements ConstraintValidator<PastDate, LocalDate> {

	@Override
	public void initialize(PastDate constraintAnnotation) {
	}

	@Override
	public boolean isValid(LocalDate dateField, ConstraintValidatorContext context) {
		if (dateField == null) {
			return false;
		}
		return dateField.isBefore(LocalDate.now());
	}
}
