package com.jisr.resolver;

import java.util.Optional;
import com.jisr.entity.User;
import com.jisr.model.Profile2;

public interface ProfileMapper {
	Profile2 mapToProfile(User user, Optional<Object> profileOptional);
}
