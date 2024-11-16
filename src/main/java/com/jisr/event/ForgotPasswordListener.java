package com.jisr.event;

import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import com.jisr.entity.User;
import com.jisr.util.EmailService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
@RequiredArgsConstructor
public class ForgotPasswordListener {

	private final EmailService emailService;

	@Async
	@EventListener
	public void handleSystemSettingChange(ForgotPasswordEvent forgotPasswordEvent) {
		User user = forgotPasswordEvent.getUser();
		String resetLink = forgotPasswordEvent.getResetLink();
		emailService.sendEmail(user.getEmail(), "Password reset", resetLink);
	}
}
