package com.jisr.service.impl;

import java.util.HashSet;
import java.util.Set;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import com.jisr.dto.PasswordResetDTO;
import com.jisr.dto.RegistrationResponse;
import com.jisr.dto.SignupDTO;
import com.jisr.entity.Role;
import com.jisr.entity.RoleEnum;
import com.jisr.entity.User;
import com.jisr.repository.RoleRepository;
import com.jisr.repository.UserRepository;
import com.jisr.security.JwtService;
import com.jisr.service.AuthService;
import com.jisr.service.SmsService;
import com.jisr.service.SystemSettingService;
import com.jisr.service.TokenService;
import com.jisr.util.EmailService;
import com.jisr.util.UserServiceUtil;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

	private final JwtService jwtService;
	private final SmsService smsService;
	private final EmailService emailService;
	private final TokenService tokenService;
	private final RoleRepository roleRepository;
	private final UserRepository userRepository;
	private final PasswordEncoder passwordEncoder;
	private final SystemSettingService globalSettingsService;
	@Value("${password-reset-request}")
	private String passwordResetRequestUrl;

	@Override
	public RegistrationResponse registerUser(SignupDTO signupDTO) {
		User user = new User();
		user.setUsername(signupDTO.getUsername());
		String firstName = signupDTO.getFirstName();
		user.setFirstName(firstName);
		String fatherName = signupDTO.getFatherName();
		user.setFatherName(fatherName);
		String lastName = signupDTO.getLastName();
		user.setLastName(lastName);
		user.setFullName(UserServiceUtil.deriveFullName(firstName, fatherName, lastName));
		user.setEmail(signupDTO.getEmail());
		user.setPhoneNumber(signupDTO.getPhoneNumber());
		user.setPasswordHash(passwordEncoder.encode(signupDTO.getPassword()));
		Role role = roleRepository.findByName(signupDTO.getRole().strip().toUpperCase())
				.orElseThrow(() -> new IllegalArgumentException("Invalid role: " + signupDTO.getRole()));
		Set<Role> roles = new HashSet<>();
		roles.add(role);
		user.setRoles(roles);
		RoleEnum userRole = RoleEnum.fromString(role.getName());

		boolean isGeneralRegistrationEnabled = globalSettingsService.isGeneralRegistrationEnabled();
		boolean isPatientRegistrationEnabled = globalSettingsService.isPatientRegistrationEnabled();
		boolean isHealthcareProviderRegistrationEnabled = globalSettingsService.isHealthcareProviderRegistrationEnabled();
		String message;
		user.setIsActive(false);
		if (isGeneralRegistrationEnabled) {
			if ((userRole == RoleEnum.PATIENT || userRole == RoleEnum.CAREGIVER) && isPatientRegistrationEnabled) {
				user.setIsActive(true);
				message = "Patient/Caregiver registered successfully and is active.";
			} else if (userRole == RoleEnum.HEALTHCARE_PROVIDER && isHealthcareProviderRegistrationEnabled) {
				user.setIsActive(true);
				message = "Healthcare Provider registered successfully and is active.";
			} else {
				message = "Registration disabled for your user type. You are in the waiting queue.";
			}
		} else {
			message = "Registration is currently disabled. You are in the waiting queue.";
		}
		User savedUser = userRepository.save(user);
		if (!savedUser.getIsActive()) {
			long position = getQueuePosition(savedUser);
			message += " Your position in the waiting queue is #" + position + ".";
		}
		return new RegistrationResponse(savedUser.getIsActive(), message);
	}

	private long getQueuePosition(User user) {
		return userRepository.countInactiveUsersBefore(user.getCreatedDate()) + 1;
	}

	public void sendPasswordResetLink(String emailOrPhone) {
		boolean isEmail = emailOrPhone.contains("@");
		if (isEmail) {
			sendPasswordResetLinkByEmail(emailOrPhone);
		} else {
			sendPasswordResetLinkByPhone(emailOrPhone);
		}
	}

	public void resetPassword(PasswordResetDTO resetDTO) {
		String token = resetDTO.getToken();
		String newPassword = resetDTO.getNewPassword();
		User user = tokenService.validateAndGetUserByPasswordResetToken(token);
		user.setPasswordHash(passwordEncoder.encode(newPassword));
		userRepository.save(user);
	}

	public String refreshAccessToken(String refreshToken) {
		if (!jwtService.validateToken(refreshToken)) {
			throw new IllegalArgumentException("Invalid refresh token");
		}
		String emailOrPhone = jwtService.getSubjectFromToken(refreshToken);
		boolean isEmail = emailOrPhone.contains("@");
		User user = null;
		if (isEmail) {
			user = userRepository.findByEmail(emailOrPhone).orElseThrow(() -> new IllegalArgumentException("User not found"));
		} else {
			user = userRepository.findByPhoneNumber(emailOrPhone).orElseThrow(() -> new IllegalArgumentException("User not found"));
		}
		return jwtService.generateAccessToken(user, emailOrPhone);
	}

	private void sendPasswordResetLinkByEmail(String email) {
		User user = userRepository.findByEmail(email).orElseThrow(() -> new IllegalArgumentException("User not found"));
		String token = tokenService.generatePasswordResetToken(user);
		String resetLink = passwordResetRequestUrl + token;
		emailService.sendEmail(email, "Password reset", resetLink);
	}

	private void sendPasswordResetLinkByPhone(String phoneNumber) {
		User user = userRepository.findByPhoneNumber(phoneNumber).orElseThrow(() -> new IllegalArgumentException("User not found"));
		String token = tokenService.generatePasswordResetToken(user);
		String resetLink = passwordResetRequestUrl + token;
		smsService.sendPasswordResetSms(phoneNumber, resetLink);
	}
}
