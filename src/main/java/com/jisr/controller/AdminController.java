package com.jisr.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.jisr.dto.HealthProviderActionDTO;
import com.jisr.service.HealthcareProviderService;
import com.jisr.service.SystemSettingService;
import com.jisr.service.WaitingQueueService;
import com.jisr.util.Constants;
import jakarta.mail.MessagingException;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/admin")
public class AdminController {

	private final SystemSettingService systemSettingService;
	private final WaitingQueueService waitingQueueService;
	private final HealthcareProviderService providerService;

	@PutMapping("/update-setting")
	public ResponseEntity<String> updateSetting(@RequestParam String key, @RequestParam String value) {
		systemSettingService.updateSetting(key, value);
		if (Constants.REGISTERATION_ENABLED.equalsIgnoreCase(key) && "true".equalsIgnoreCase(value)) {
			waitingQueueService.notifyQueuedUsersIfEnabled();
		}
		return ResponseEntity.ok("Setting updated successfully.");
	}

	@PostMapping("/provider/action")
	public ResponseEntity<?> doAction(@RequestBody @Valid HealthProviderActionDTO healthProviderActionDTO) {
		try {
			providerService.approveProvider(healthProviderActionDTO);
			return ResponseEntity.ok("Provider approved.");
		} catch (MessagingException e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to send email.");
		}
	}
}
