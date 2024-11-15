package com.jisr.entity;

import java.util.Arrays;
import org.springframework.security.core.GrantedAuthority;

public enum RoleEnum implements GrantedAuthority {
	ADMIN("ADMIN"),
	PATIENT("PATIENT"),
	CAREGIVER("CAREGIVER"),
	HEALTHCARE_PROVIDER("HEALTHCARE_PROVIDER");

	private final String roleName;

	RoleEnum(String roleName) {
		this.roleName = roleName;
	}

	public String getRoleName() {
		return roleName;
	}

	public static RoleEnum fromString(String role) {
		return Arrays.stream(RoleEnum.values()).filter(r -> r.roleName.equalsIgnoreCase(role)).findFirst()
				.orElseThrow(() -> new IllegalArgumentException("Invalid role: " + role));
	}

	@Override
	public String getAuthority() {
		return name();
	}
}
