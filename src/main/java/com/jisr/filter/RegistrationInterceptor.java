package com.jisr.filter;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jisr.dto.PatientDTO;
import com.jisr.exception.RegistrationDisabledException; // Add import
import com.jisr.service.SystemSettingService;
import com.jisr.service.WaitingQueueService;
import com.jisr.util.Constants;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class RegistrationInterceptor implements HandlerInterceptor {

	private final SystemSettingService systemSettingService;
	private final WaitingQueueService waitingQueueService;
	private final ObjectMapper objectMapper;

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		if ("/api/auth/patients/signup".equals(request.getRequestURI()) && request.getMethod().equalsIgnoreCase("POST")) {
			if (!systemSettingService.isSettingEnabled(Constants.REGISTERATION_ENABLED)) {
				String requestBody = RequestBodyCachingFilter.getCachedRequestBody();
				if (requestBody == null || requestBody.isEmpty()) {
					throw new RegistrationDisabledException("Empty request body");
				}
				PatientDTO userDTO = objectMapper.readValue(requestBody, PatientDTO.class);
				String username = userDTO.getUsername();
				Long positionInQueue = waitingQueueService.getPositionInQueue(username);
				// If position is null, the user is not in the queue, so add them
				if (positionInQueue == null) {
					waitingQueueService.addToWaitingQueue(userDTO, userDTO.getRole());
					positionInQueue = waitingQueueService.getPositionInQueue(username); // Re-fetch the position
				}
				throw new RegistrationDisabledException(
						"Registration is disabled. Your position in the queue is " + (positionInQueue != null ? positionInQueue : "unknown"));
			}
		}
		return true;
	}
}
