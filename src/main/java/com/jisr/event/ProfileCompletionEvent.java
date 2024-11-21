package com.jisr.event;

import org.springframework.context.ApplicationEvent;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ProfileCompletionEvent extends ApplicationEvent {

	private static final long serialVersionUID = 8530801957854196670L;

	private final Long userId;
	private final EventType eventType;
	private Long adminId;
	private Long patientId;
	private Long healthcareProviderId;

	public enum EventType {
		USER,
		ADMIN,
		PATIENT,
		HEALTHCARE_PROVIDER
	}

	private ProfileCompletionEvent(Object source, Long userId, EventType eventType) {
		super(source);
		this.userId = userId;
		this.eventType = eventType;
	}

	public static ProfileCompletionEvent forUser(Object source, Long userId) {
		return new ProfileCompletionEvent(source, userId, EventType.USER);
	}

	public static ProfileCompletionEvent forAdmin(Object source, Long userId, Long adminId) {
		ProfileCompletionEvent profileCompletionEvent = new ProfileCompletionEvent(source, userId, EventType.ADMIN);
		profileCompletionEvent.setAdminId(adminId);
		return profileCompletionEvent;
	}

	public static ProfileCompletionEvent forPatient(Object source, Long userId, Long patientId) {
		ProfileCompletionEvent profileCompletionEvent = new ProfileCompletionEvent(source, userId, EventType.PATIENT);
		profileCompletionEvent.setPatientId(patientId);
		return profileCompletionEvent;
	}

	public static ProfileCompletionEvent forHealthcareProvider(Object source, Long userId, Long healthcareProviderId) {
		ProfileCompletionEvent profileCompletionEvent = new ProfileCompletionEvent(source, userId, EventType.HEALTHCARE_PROVIDER);
		profileCompletionEvent.setHealthcareProviderId(healthcareProviderId);
		return profileCompletionEvent;
	}

}
