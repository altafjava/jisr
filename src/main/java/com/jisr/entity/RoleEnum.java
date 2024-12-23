package com.jisr.entity;

import java.util.Arrays;
import java.util.List;
import org.springframework.security.core.GrantedAuthority;
import lombok.Getter;

@Getter
public enum RoleEnum implements GrantedAuthority {
	ADMIN("ADMIN", List.of(User.class.getSimpleName())),
	PATIENT("PATIENT", List.of(User.class.getSimpleName(), PatientProfile.class.getSimpleName())),
	CAREGIVER("CAREGIVER", List.of(User.class.getSimpleName(), PatientProfile.class.getSimpleName())),
	HEALTHCARE_PROVIDER("HEALTHCARE_PROVIDER", List.of(User.class.getSimpleName(), HealthcareProvider.class.getSimpleName()));

	private final String roleName;
	private final List<String> entityNames;

	RoleEnum(String roleName, List<String> entityNames) {
		this.roleName = roleName;
		this.entityNames = entityNames;
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
