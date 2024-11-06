package com.jisr.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.jisr.service.SystemSettingService;
import com.jisr.service.WaitingQueueService;
import com.jisr.util.Constants;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/admin")
public class AdminController {

	private final SystemSettingService systemSettingService;
	private final WaitingQueueService waitingQueueService;

	@PutMapping("/update-setting")
	public ResponseEntity<String> updateSetting(@RequestParam String key, @RequestParam String value) {
		systemSettingService.updateSetting(key, value);
		if (Constants.REGISTERATION_ENABLED.equalsIgnoreCase(key) && "true".equalsIgnoreCase(value)) {
			waitingQueueService.notifyQueuedUsersIfEnabled();
		}
		return ResponseEntity.ok("Setting updated successfully.");
	}
}
