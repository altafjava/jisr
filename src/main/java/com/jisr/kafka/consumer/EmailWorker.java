package com.jisr.kafka.consumer;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import com.jisr.kafka.constant.Topics;
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

	@KafkaListener(id = "com.jisr.kafka.consumer.EmailWorker", topics = Topics.EMAIL_NOTIFICATION, groupId = "com.jisr.kafka.consumer", containerFactory = "kafkaListenerContainerFactory")
	public void listen(@Header(KafkaHeaders.RECEIVED_PARTITION) Integer partition, @Header(KafkaHeaders.RECEIVED_KEY) String key,
			@Payload EmailNotification emailNotification) {
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
