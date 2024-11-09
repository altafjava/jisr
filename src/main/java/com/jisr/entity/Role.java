package com.jisr.entity;

import org.springframework.security.core.GrantedAuthority;

public enum Role implements GrantedAuthority {
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

	@Override
	public String getAuthority() {
		return name();
	}
}
