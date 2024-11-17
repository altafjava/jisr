package com.jisr.resolver;

import java.util.Optional;
import org.springframework.stereotype.Component;

@Component
public class AdminProfileResolver implements ProfileResolver {

	@Override
	public Optional<Object> resolveProfile(Long userId) {
		return Optional.empty();
	}
}
