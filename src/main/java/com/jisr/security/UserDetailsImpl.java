package com.jisr.security;

import java.util.Collection;
import java.util.Collections;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import com.jisr.entity.Patient;

public class UserDetailsImpl implements UserDetails {

	private static final long serialVersionUID = 1L;
	private final Patient patient;

	public UserDetailsImpl(Patient user) {
		this.patient = user;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return Collections.singletonList(new SimpleGrantedAuthority(patient.getRole().name()));
	}

	@Override
	public String getPassword() {
		return patient.getPassword();
	}

	@Override
	public String getUsername() {
		return patient.getUsername(); // Or `user.getEmail()` based on your login strategy
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}

	public Patient getUser() {
		return patient;
	}
	
	public Long getId() {
        return patient.getId();
    }

}
