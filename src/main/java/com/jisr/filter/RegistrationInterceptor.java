//package com.jisr.filter;
//
//import org.springframework.stereotype.Component;
//import org.springframework.web.servlet.HandlerInterceptor;
//import com.fasterxml.jackson.databind.ObjectMapper;
//import com.jisr.constant.GlobalSettingConstant;
//import com.jisr.dto.PatientAuthDTO;
//import com.jisr.exception.RegistrationDisabledException; // Add import
//import com.jisr.service.GlobalSettingsService;
//import com.jisr.service.WaitingQueueService;
//import jakarta.servlet.http.HttpServletRequest;
//import jakarta.servlet.http.HttpServletResponse;
//import lombok.RequiredArgsConstructor;
//
//@Component
//@RequiredArgsConstructor
//public class RegistrationInterceptor implements HandlerInterceptor {
//
//	private final GlobalSettingsService systemSettingService;
//	private final WaitingQueueService waitingQueueService;
//	private final ObjectMapper objectMapper;
//
//	@Override
//	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
//		if ("/api/auth/signup".equals(request.getRequestURI()) && request.getMethod().equalsIgnoreCase("POST")) {
//			if (!systemSettingService.getSetting(GlobalSettingConstant.PATIENT_REGISTRATION_ENABLED).isEnabled()) {
//				String requestBody = RequestBodyCachingFilter.getCachedRequestBody();
//				if (requestBody == null || requestBody.isEmpty()) {
//					throw new RegistrationDisabledException("Empty request body");
//				}
//				PatientAuthDTO userDTO = objectMapper.readValue(requestBody, PatientAuthDTO.class);
//				String username = userDTO.getUsername();
//				Long positionInQueue = waitingQueueService.getPositionInQueue(username);
//				// If position is null, the user is not in the queue, so add them
//				if (positionInQueue == null) {
//					waitingQueueService.addToWaitingQueue(userDTO, userDTO.getRole());
//					positionInQueue = waitingQueueService.getPositionInQueue(username); // Re-fetch the position
//				}
//				throw new RegistrationDisabledException(
//						"Registration is disabled. Your position in the queue is " + (positionInQueue != null ? positionInQueue : "unknown"));
//			}
//		}
//		return true;
//	}
//}
