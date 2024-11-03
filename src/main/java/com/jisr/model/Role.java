package com.jisr.model;

public enum Role {
	ADMIN("admin"),
	PATIENT("patient"),
	CAREGIVER("caregiver"),
	HEALTHCARE_PROVIDER("healthcare_provider");

	private final String roleName;

	Role(String roleName) {
		this.roleName = roleName;
	}

	public String getRoleName() {
		return roleName;
	}
}
