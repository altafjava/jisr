package com.jisr.util;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class EmailService {

	private final JavaMailSender mailSender;

	public void notifyRegistrationOpen(String email, String username) {
		String subject = "Registration Queue Notification";
		String body = String.format("Dear %s, Registration is now open! Please complete your registration", username);
		SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
		simpleMailMessage.setTo(email);
		simpleMailMessage.setSubject(subject);
		simpleMailMessage.setText(body);
		mailSender.send(simpleMailMessage);
	}

	public void sendEmail(String to, String subject, String body) {
		SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
		simpleMailMessage.setTo(to);
		simpleMailMessage.setSubject(subject);
		simpleMailMessage.setText(body);
		mailSender.send(simpleMailMessage);
	}

}
