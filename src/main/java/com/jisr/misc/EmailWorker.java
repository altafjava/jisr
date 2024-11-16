package com.jisr.misc;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import com.jisr.model.EmailNotification;
import com.jisr.util.EmailService;
import lombok.RequiredArgsConstructor;

/**
 * Kafka Consumer
 */
@Service
@RequiredArgsConstructor
public class EmailWorker {

	private final EmailService emailService;

	@KafkaListener(topics = "email-notification-topic", groupId = "email-notification-group")
	public void consume(EmailNotification emailNotification) {
		sendEmailAsync(emailNotification);
	}

	@Async
	public void sendEmailAsync(EmailNotification emailNotification) {
		String subject = emailNotification.getSubject();
		String body = emailNotification.getBody();
		String to = emailNotification.getTo();
		emailService.sendEmail(to, subject, body);
	}
}
