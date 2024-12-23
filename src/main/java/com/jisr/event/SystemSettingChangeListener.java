package com.jisr.event;

import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import com.jisr.constant.SystemSettingConstant;
import com.jisr.entity.SystemSetting;
import com.jisr.service.NotificationService;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class SystemSettingChangeListener {

	private final NotificationService notificationService;

	@EventListener
	public void handleSystemSettingChange(SystemSettingChangedEvent systemSettingChangedEvent) {
		SystemSetting systemSetting = systemSettingChangedEvent.getSystemSetting();
		String settingName = systemSetting.getName();
		boolean isEnabled = systemSetting.isEnabled();
		if (SystemSettingConstant.GENERAL_REGISTRATION_ENABLED.equals(settingName)) {
			if (isEnabled) {
				notificationService.notifyPatientsAndCaregivers();
			}
		} else if (SystemSettingConstant.PATIENT_REGISTRATION_ENABLED.equals(settingName)) {
			if (isEnabled) {
				notificationService.notifyPatients();
			}
		} else if (SystemSettingConstant.HEALTHCARE_PROVIDER_REGISTRATION_ENABLED.equals(settingName)) {
			if (isEnabled) {
				notificationService.notifyHealthcareProviders();
			}
		}
	}
}
