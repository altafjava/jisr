package com.jisr.resolver;

import java.util.Optional;

public interface ProfileResolver {
	Optional<Object> resolveProfile(Long userId);
}
