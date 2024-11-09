package com.jisr.service;

import org.springframework.stereotype.Service;
import com.jisr.util.SmsSender;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class SmsService {

    private final SmsSender smsSender;

    // Method to send a password reset link via SMS
    public void sendPasswordResetSms(String phoneNumber, String resetLink) {
        String message = "To reset your password, please visit: " + resetLink;
        smsSender.sendSms(phoneNumber, message);
    }
}
