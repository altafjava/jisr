package com.jisr.service;

import java.util.Map;
import java.util.stream.Collectors;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.jisr.constant.SystemSettingConstant;
import com.jisr.dto.SystemSettingRequest;
import com.jisr.entity.SystemSetting;
import com.jisr.event.SystemSettingChangedEvent;
import com.jisr.repository.GlobalSettingRepository;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class SystemSettingService {

	private final GlobalSettingRepository globalSettingRepository;
    private final ApplicationEventPublisher applicationEventPublisher;

	@Cacheable("systemSettings")
	public Map<String, SystemSetting> getAllSettings() {
		return globalSettingRepository.findAll().stream().collect(Collectors.toMap(SystemSetting::getName, setting -> setting));
	}

	@PostConstruct
	public void initSettingsCache() {
		getAllSettings();
	}

	@Transactional
	@CacheEvict(value = "systemSettings", allEntries = true)
	public void updateSetting(SystemSettingRequest systemSettingRequest) {
        SystemSetting systemSetting = globalSettingRepository.findByName(systemSettingRequest.getName())
                .orElseThrow(() -> new IllegalArgumentException("Setting not found: " + systemSettingRequest.getName()));
        systemSetting.setValue(systemSettingRequest.getValue());
        systemSetting.setEnabled(systemSettingRequest.isEnabled());
        systemSetting.setDisplayName(systemSettingRequest.getDisplayName());
        systemSetting.setDescription(systemSettingRequest.getDescription());
        globalSettingRepository.save(systemSetting);
        SystemSettingChangedEvent event = new SystemSettingChangedEvent(this, systemSetting);
        applicationEventPublisher.publishEvent(event);
    }

	public SystemSetting getSetting(String name) {
		return getAllSettings().get(name);
	}

	public boolean isGeneralRegistrationEnabled() {
		return getSetting(SystemSettingConstant.GENERAL_REGISTRATION_ENABLED).isEnabled();
	}

	public boolean isPatientRegistrationEnabled() {
		return getSetting(SystemSettingConstant.PATIENT_REGISTRATION_ENABLED).isEnabled();
	}

	public boolean isHealthcareProviderRegistrationEnabled() {
		return getSetting(SystemSettingConstant.HEALTHCARE_PROVIDER_REGISTRATION_ENABLED).isEnabled();
	}

	public boolean isFileUploadEnabled() {
		return getSetting(SystemSettingConstant.FILE_UPLOAD_ENABLED).isEnabled();
	}
}
