package com.jisr.validator;

public enum Gender {
	MALE,
	FEMALE;

	public static Gender fromString(String value) {
		if (value == null) {
			return null; // If gender is not provided, return null.
		}
		// Convert value to uppercase and check for valid enum
		try {
			return Gender.valueOf(value.trim().toUpperCase());
		} catch (IllegalArgumentException e) {
			throw new IllegalArgumentException("Gender must be either 'Male' or 'Female'");
		}
	}
}

//public enum Gender {
//	MALE,
//	FEMALE;
//
//	public static Gender fromString(String value) {
//		if (value == null) {
//			throw new IllegalArgumentException("Gender cannot be null");
//		}
//		for (Gender gender : Gender.values()) {
//			if (gender.name().equalsIgnoreCase(value)) {
//				return gender;
//			}
//		}
//		throw new IllegalArgumentException("Invalid gender value: " + value);
//	}
//}
