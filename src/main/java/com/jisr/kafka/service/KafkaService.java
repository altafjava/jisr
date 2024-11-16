package com.jisr.kafka.service;

import java.util.concurrent.ExecutionException;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class KafkaService {

	private final KafkaTemplate<String, Object> kafkaTemplate;

	public void sendMessage(Object payload, String key, String topic) {
		try {
			Message<Object> message = MessageBuilder.withPayload(payload)
					.setHeader(KafkaHeaders.KEY, key)
					.setHeader(KafkaHeaders.TOPIC, topic)
					.build();
			kafkaTemplate.send(message).get();
		} catch (InterruptedException | ExecutionException e) {
			e.printStackTrace();
		}
	}
}
