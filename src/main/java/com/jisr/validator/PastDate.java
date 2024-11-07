package com.jisr.validator;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

@Constraint(validatedBy = PastDateValidator.class)
@Target({ ElementType.FIELD, ElementType.METHOD })
@Retention(RetentionPolicy.RUNTIME)
public @interface PastDate {
	String message() default "Date must be in the past and in the format dd-MM-YYYY";

	Class<?>[] groups() default {};

	Class<? extends Payload>[] payload() default {};
}
