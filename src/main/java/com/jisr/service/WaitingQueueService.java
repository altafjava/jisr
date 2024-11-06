package com.jisr.service;

import java.util.List;
import java.util.Optional;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.jisr.dto.UserDTO;
import com.jisr.model.Role;
import com.jisr.model.WaitingQueue;
import com.jisr.repository.WaitingQueueRepository;
import com.jisr.util.Constants;
import com.jisr.util.EmailService;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class WaitingQueueService {

	private final EmailService emailService;
	private final PasswordEncoder passwordEncoder;
	private final SystemSettingService systemSettingService;
	private final WaitingQueueRepository waitingQueueRepository;

	public Long getPositionInQueue(String username) {
		Optional<WaitingQueue> userQueueOptional = waitingQueueRepository.findByUsername(username);
		if (userQueueOptional.isEmpty()) {
			return null;
		}
		WaitingQueue waitingQueue = userQueueOptional.get();
		Long position = waitingQueueRepository.countByCreatedAtBefore(waitingQueue.getCreatedAt());
		return position + 1;
	}

	@Transactional
	public Long addToWaitingQueue(UserDTO userDTO, Role role) {
		WaitingQueue waitingQueue = new WaitingQueue();
		waitingQueue.setUsername(userDTO.getUsername());
		waitingQueue.setFirstName(userDTO.getFirstName());
		waitingQueue.setFatherName(userDTO.getFatherName());
		waitingQueue.setLastName(userDTO.getLastName());
		waitingQueue.setEmail(userDTO.getEmail());
		waitingQueue.setPhoneNumber(userDTO.getPhoneNumber());
		waitingQueue.setPassword(passwordEncoder.encode(userDTO.getPassword()));
		waitingQueue.setRole(role);
		waitingQueue.setRelationship(userDTO.getRelationship());
		waitingQueueRepository.save(waitingQueue);
		return getPositionInQueue(userDTO.getUsername());
	}

	public List<WaitingQueue> getAllQueuedUsers() {
		return waitingQueueRepository.findAll();
	}

	public void clearQueue() {
		waitingQueueRepository.deleteAll();
	}

	@Transactional
	public void notifyQueuedUsersIfEnabled() {
		if (systemSettingService.isSettingEnabled(Constants.REGISTERATION_ENABLED)) {
			List<WaitingQueue> queuedUsers = getAllQueuedUsers();
			for (WaitingQueue user : queuedUsers) {
				emailService.notifyRegistrationOpen(user.getEmail(), user.getUsername());
			}
		}
		clearQueue();
	}
}
