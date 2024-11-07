package com.jisr.validator;

import java.util.Arrays;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class EnumValidatorImpl implements ConstraintValidator<EnumValidator, String> {
	private Class<? extends Enum<?>> enumClass;

	@Override
	public void initialize(EnumValidator constraintAnnotation) {
		this.enumClass = constraintAnnotation.enumClass();
	}

	@Override
	public boolean isValid(String value, ConstraintValidatorContext context) {
		if (value == null) {
			return true;
		}
		return Arrays.stream(enumClass.getEnumConstants()).anyMatch(e -> e.name().equals(value));
	}
}
