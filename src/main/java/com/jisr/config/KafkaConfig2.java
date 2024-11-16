//package com.jisr.config;
//
//import java.util.HashMap;
//import java.util.Map;
//import org.apache.kafka.clients.consumer.ConsumerConfig;
//import org.apache.kafka.clients.consumer.ConsumerRecord;
//import org.apache.kafka.clients.producer.ProducerConfig;
//import org.apache.kafka.common.serialization.StringDeserializer;
//import org.apache.kafka.common.serialization.StringSerializer;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.kafka.annotation.EnableKafka;
//import org.springframework.kafka.core.ConsumerFactory;
//import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
//import org.springframework.kafka.core.DefaultKafkaProducerFactory;
//import org.springframework.kafka.core.KafkaTemplate;
//import org.springframework.kafka.core.ProducerFactory;
//import org.springframework.kafka.listener.ConcurrentMessageListenerContainer;
//import org.springframework.kafka.listener.ContainerProperties;
//import org.springframework.kafka.listener.MessageListener;
//import org.springframework.kafka.support.serializer.JsonDeserializer;
//import org.springframework.kafka.support.serializer.JsonSerializer;
//import com.jisr.model.EmailNotification;
//
///**
// * Kafka Listener Container Configuration
// */
//@Configuration
//@EnableKafka
//public class KafkaConfig2 {
//
//	@Value("${kafka.bootstrap.servers}")
//	private String bootstrapServers;
//	private static final String EMAIL_NOTIFICATION_TOPIC = "email-notification-topic";
//
//	@Bean
//	public ProducerFactory<String, EmailNotification> producerFactory() {
//		Map<String, Object> configProps = new HashMap<>();
//		configProps.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
//		configProps.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
//		configProps.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, JsonSerializer.class);
//		return new DefaultKafkaProducerFactory<>(configProps);
//	}
//
//	@Bean
//	public KafkaTemplate<String, EmailNotification> kafkaTemplate() {
//		return new KafkaTemplate<>(producerFactory());
//	}
//
//	@Bean
//	public ConsumerFactory<String, EmailNotification> consumerFactory() {
//		Map<String, Object> configProps = new HashMap<>();
//		configProps.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
//		configProps.put(ConsumerConfig.GROUP_ID_CONFIG, "email-notification-group");
//		configProps.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
//		configProps.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, JsonDeserializer.class);
//		return new DefaultKafkaConsumerFactory<>(configProps, new StringDeserializer(), new JsonDeserializer<>(EmailNotification.class));
//	}
//
//	@Bean
//	public ConcurrentMessageListenerContainer<String, EmailNotification> emailNotificationListenerContainer() {
//		// Define the container properties
//		ContainerProperties containerProps = new ContainerProperties(EMAIL_NOTIFICATION_TOPIC);
//		// Define the listener
//		MessageListener<String, EmailNotification> messageListener = new MessageListener<String, EmailNotification>() {
//			@Override
//			public void onMessage(ConsumerRecord<String, EmailNotification> record) {
//				EmailNotification emailNotification = record.value();
//				// Process the email notification here
//				System.out.println("Received Email Notification: " + emailNotification);
//				// Call your email service to send the email
//			}
//		};
//		// Set the message listener to the container properties
//		containerProps.setMessageListener(messageListener);
//		// Return the ConcurrentMessageListenerContainer with the correct parameters
//		return new ConcurrentMessageListenerContainer<>(consumerFactory(), containerProps);
//	}
//
//}
