package com.jisr.util;

public class CaseUtils {
	
	public static String toCamelCase(String snakeCase) {
		StringBuilder result = new StringBuilder();
		boolean nextUpperCase = false;
		for (char ch : snakeCase.toCharArray()) {
			if (ch == '_') {
				nextUpperCase = true;
			} else if (nextUpperCase) {
				result.append(Character.toUpperCase(ch));
				nextUpperCase = false;
			} else {
				result.append(ch);
			}
		}
		return result.toString();
	}
}
