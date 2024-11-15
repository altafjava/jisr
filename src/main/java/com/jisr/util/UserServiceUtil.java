package com.jisr.util;

public class UserServiceUtil {

	public static String deriveFullName(String firstName, String fatherName, String lastName) {
		StringBuilder nameBuilder = new StringBuilder();
		nameBuilder.append(firstName);
		if (fatherName != null && !fatherName.isEmpty()) {
			nameBuilder.append(" ").append(fatherName);
		}
		nameBuilder.append(" ").append(lastName);
		return nameBuilder.toString().trim();
	}

}
