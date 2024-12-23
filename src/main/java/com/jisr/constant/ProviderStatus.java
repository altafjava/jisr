package com.jisr.constant;

public enum ProviderStatus {
	DRAFTED,
	PENDING,
	APPROVED,
	REJECTED;

	public static ProviderStatus fromString(String status) {
		if (status == null) {
			return null;
		}
		try {
			return ProviderStatus.valueOf(status.toUpperCase());
		} catch (IllegalArgumentException e) {
			return null;
		}
	}

	public String asString() {
		return this.name();
	}
}
