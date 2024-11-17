package com.jisr.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.jisr.dto.SystemSettingRequest;
import com.jisr.service.SystemSettingService;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/admin")
public class AdminController {

	private final SystemSettingService systemSettingService;
//	private final HealthcareProviderService providerService;

	@PutMapping("/settings")
	public ResponseEntity<String> updateSetting(@RequestBody SystemSettingRequest systemSettingRequest) {
		try {
			systemSettingService.updateSetting(systemSettingRequest);
			return ResponseEntity.ok("Setting updated successfully");
		} catch (IllegalArgumentException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
		}
	}

//	@PostMapping("/provider/action")
//	public ResponseEntity<?> doAction(@RequestBody @Valid HealthProviderActionDTO healthProviderActionDTO) {
//		try {
//			providerService.approveProvider(healthProviderActionDTO);
//			return ResponseEntity.ok("Provider approved.");
//		} catch (MessagingException e) {
//			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to send email.");
//		}
//	}
}
