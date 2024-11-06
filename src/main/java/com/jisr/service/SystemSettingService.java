package com.jisr.service;

import org.springframework.stereotype.Service;
import com.jisr.repository.SystemSettingRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class SystemSettingService {

	private final SystemSettingRepository systemSettingRepository;

	public void updateSetting(String key, String value) {
		systemSettingRepository.updateValueByKey(key, value);
	}

	public boolean isSettingEnabled(String key) {
		String value = systemSettingRepository.findValueByKey(key);
		return "true".equalsIgnoreCase(value);
	}
}
