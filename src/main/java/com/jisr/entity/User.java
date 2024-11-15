package com.jisr.entity;

import java.util.HashSet;
import java.util.Set;
import com.jisr.entity.core.AuditableEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "users")
public class User extends AuditableEntity<Long> {

	private static final long serialVersionUID = 2823376205290825638L;

	@ManyToMany
	@JoinTable(name = "user_roles", joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"), inverseJoinColumns = @JoinColumn(name = "role_id", referencedColumnName = "id"))
	private Set<Role> roles = new HashSet<>();

	@Column(name = "username", length = 20, nullable = false, unique = true)
	private String username;

	@Column(name = "first_name", length = 100, nullable = false)
	private String firstName;

	@Column(name = "father_name", length = 100)
	private String fatherName;

	@Column(name = "last_name", length = 100, nullable = false)
	private String lastName;

	@Column(name = "full_name", length = 300, nullable = false)
	private String fullName;

	@Column(name = "email", length = 100, nullable = false, unique = true)
	private String email;

	@Column(name = "phone_number", length = 15, nullable = false, unique = true)
	private String phoneNumber;

	@Column(name = "password_hash", length = 255, nullable = false)
	private String passwordHash;

	@Column(name = "is_active", nullable = false)
	private Boolean isActive = false;

}
