package com.jisr.constant;

public enum HealthProviderStatus {
	PENDING,
	APPROVED,
	REJECTED;

	public static HealthProviderStatus fromString(String status) {
		if (status == null) {
			return null;
		}
		try {
			return HealthProviderStatus.valueOf(status.toUpperCase());
		} catch (IllegalArgumentException e) {
			return null;
		}
	}

	public String asString() {
		return this.name();
	}
}
