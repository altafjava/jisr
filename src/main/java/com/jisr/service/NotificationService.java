package com.jisr.service;

import java.util.List;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import com.jisr.entity.RoleEnum;
import com.jisr.entity.User;
import com.jisr.model.EmailNotification;
import lombok.RequiredArgsConstructor;

/**
 * Kafka Producer
 */
@Service
@RequiredArgsConstructor
public class NotificationService {

	private final UserActivationService userActivationService;
	private final KafkaTemplate<String, EmailNotification> kafkaTemplate;
	private static final String EMAIL_NOTIFICATION_TOPIC = "email-notification-topic";

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
			sendEmailNotificationToKafka(emailNotification);
		}
	}

	private void sendEmailNotificationToKafka(EmailNotification emailNotification) {
		kafkaTemplate.send(EMAIL_NOTIFICATION_TOPIC, emailNotification);
	}
}