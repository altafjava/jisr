package com.jisr.service;

import java.util.List;
import java.util.UUID;
import org.springframework.stereotype.Service;
import com.jisr.constant.ProviderStatus;
import com.jisr.entity.HealthcareProvider;
import com.jisr.entity.RoleEnum;
import com.jisr.entity.User;
import com.jisr.kafka.constant.Topics;
import com.jisr.kafka.service.KafkaService;
import com.jisr.model.EmailNotification;
import lombok.RequiredArgsConstructor;

/**
 * Kafka Producer
 */
@Service
@RequiredArgsConstructor
public class NotificationService {

	private final KafkaService kafkaService;
	private final UserActivationService userActivationService;

	public void notifyHealthcareProviderAccountStatus(HealthcareProvider provider, ProviderStatus status) {
		User user = provider.getUser();
		String subject = "Account Status";
		String body = "Hello " + user.getUsername() + ",\n\nYour account has been " + status;
		EmailNotification emailNotification = new EmailNotification(user.getEmail(), subject, body);
		kafkaService.sendMessage(emailNotification, UUID.randomUUID().toString(), Topics.EMAIL_NOTIFICATION);
	}

	public void notifyPatientsAndCaregivers() {
		List<String> roleNames = List.of(RoleEnum.PATIENT.getRoleName(), RoleEnum.CAREGIVER.getRoleName());
		notifyUsers(roleNames);
	}

	public void notifyPatients() {
		List<String> roleNames = List.of(RoleEnum.PATIENT.getRoleName());
		notifyUsers(roleNames);
	}

	public void notifyHealthcareProviders() {
		List<String> roleNames = List.of(RoleEnum.HEALTHCARE_PROVIDER.getRoleName());
		notifyUsers(roleNames);
	}

	private void notifyUsers(List<String> roleNames) {
		List<User> usersToNotify = userActivationService.activateUsersByRoles(roleNames);
		String subject = "Your Registration is Now Open!";
		for (User user : usersToNotify) {
			String body = "Hello " + user.getUsername() + ",\n\nYour registration has been successfully activated. You can now login to your account.";
			EmailNotification emailNotification = new EmailNotification(user.getEmail(), subject, body);
			kafkaService.sendMessage(emailNotification, UUID.randomUUID().toString(), Topics.EMAIL_NOTIFICATION);
		}
	}

}