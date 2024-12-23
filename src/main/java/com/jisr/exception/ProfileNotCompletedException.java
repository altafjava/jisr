package com.jisr.exception;

import com.jisr.entity.User;
import lombok.Getter;

@Getter
public class ProfileNotCompletedException extends RuntimeException {

	private static final long serialVersionUID = -4288925494442160974L;
	private User user;

	public ProfileNotCompletedException(User user, String string) {
		super(string);
		this.user = user;
	}
}
