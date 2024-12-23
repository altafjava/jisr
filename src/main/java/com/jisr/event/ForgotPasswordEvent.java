package com.jisr.event;

import org.springframework.context.ApplicationEvent;
import com.jisr.entity.User;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ForgotPasswordEvent extends ApplicationEvent {

	private static final long serialVersionUID = 3404305055765247434L;

	private final User user;
	private final String resetLink;

	public ForgotPasswordEvent(Object source, User user, String resetLink) {
		super(source);
		this.user = user;
		this.resetLink = resetLink;
	}

}
