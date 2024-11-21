package com.jisr.controller;

import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.jisr.dto.SystemSettingRequest;
import com.jisr.entity.SystemSetting;
import com.jisr.service.SystemSettingService;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/admins/settings")
public class AdminSettingController {

	private final SystemSettingService systemSettingService;

	@GetMapping
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public List<SystemSetting> getAllSystemSettings(){
		return systemSettingService.findAllSystemSettings();
	}
	
	@PutMapping
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public ResponseEntity<String> updateSetting(@RequestBody SystemSettingRequest systemSettingRequest) {
		try {
			systemSettingService.updateSetting(systemSettingRequest);
			return ResponseEntity.ok("Setting updated successfully");
		} catch (IllegalArgumentException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
		}
	}

}
