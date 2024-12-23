package com.jisr.controller;

import java.util.List;
import java.util.Map;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.jisr.entity.Role;
import com.jisr.service.RoleService;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/roles")
@RequiredArgsConstructor
public class RoleController {

	private final RoleService roleService;

	@GetMapping
	public ResponseEntity<List<Role>> getAllRoles() {
		List<Role> roles = roleService.getAllRoles();
		return new ResponseEntity<>(roles, HttpStatus.OK);
	}

	@GetMapping("/{id}")
	public ResponseEntity<Role> getRoleById(@PathVariable Long id) {
		return roleService.getRoleById(id).map(role -> new ResponseEntity<>(role, HttpStatus.OK)).orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
	}

	@PreAuthorize("hasRole('ADMIN')")
	@PostMapping
	public ResponseEntity<Role> createRole(@RequestBody Map<String, String> request) {
	    String name = request.get("name");
	    Role createdRole = roleService.createRole(name);
	    return new ResponseEntity<>(createdRole, HttpStatus.CREATED);
	}

	@PreAuthorize("hasRole('ADMIN')")
	@PutMapping("/{id}")
	public ResponseEntity<Role> updateRole(@PathVariable Long id, @RequestBody Map<String, String> request) {
		try {
			String name = request.get("name");
			Role updatedRole = roleService.updateRole(id, name);
			return new ResponseEntity<>(updatedRole, HttpStatus.OK);
		} catch (IllegalArgumentException e) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
	}

	@PreAuthorize("hasRole('ADMIN')")
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteRole(@PathVariable Long id) {
		try {
			roleService.deleteRole(id);
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} catch (IllegalArgumentException e) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
}
