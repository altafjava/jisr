package com.jisr.dto;

import java.util.Set;
import com.jisr.entity.Role;
import com.jisr.entity.RoleEnum;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserDTO {
	private Set<Role> roles;
	private String username;
	private String firstName;
	private String fatherName;
	private String lastName;
	private String fullName;
	private String email;
	private String phoneNumber;
	private RoleEnum userType;
	private int profileCompletion;
	private Boolean isActive;
}
