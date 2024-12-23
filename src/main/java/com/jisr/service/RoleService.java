package com.jisr.service;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.jisr.entity.Role;
import com.jisr.exception.RoleNotFoundException;
import com.jisr.repository.RoleRepository;

@Service
@Transactional
public class RoleService {

	private final RoleRepository roleRepository;

	@Autowired
	public RoleService(RoleRepository roleRepository) {
		this.roleRepository = roleRepository;
	}

	public Role createRole(String roleName) {
		if (roleRepository.existsByName(roleName)) {
			throw new IllegalArgumentException("Role with name " + roleName + " already exists.");
		}
		Role role = new Role();
		role.setName(roleName);
		return roleRepository.save(role);
	}

	public List<Role> getAllRoles() {
		return roleRepository.findAll();
	}

	public Optional<Role> getRoleById(Long roleId) {
		return roleRepository.findById(roleId);
	}

	public Role updateRole(Long roleId, String newRoleName) {
		Role role = roleRepository.findById(roleId).orElseThrow(() -> new RoleNotFoundException("Role not found with ID: " + roleId));
		if (!role.getName().equals(newRoleName) && roleRepository.existsByName(newRoleName)) {
			throw new IllegalArgumentException("Role with name " + newRoleName + " already exists.");
		}
		role.setName(newRoleName);
		return roleRepository.save(role);
	}

	public void deleteRole(Long roleId) {
		if (!roleRepository.existsById(roleId)) {
			throw new IllegalArgumentException("Role not found with ID: " + roleId);
		}
		roleRepository.deleteById(roleId);
	}
}
