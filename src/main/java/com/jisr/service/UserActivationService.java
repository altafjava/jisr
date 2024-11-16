package com.jisr.service;

import java.util.List;
import org.springframework.stereotype.Service;
import com.jisr.entity.User;
import com.jisr.repository.UserRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserActivationService {

    private final UserRepository userRepository;

    public List<User> activateUsersByRoles(List<String> roleNames) {
        List<User> inactiveUsers = userRepository.findInactiveUsersByRoles(roleNames);
        for (User user : inactiveUsers) {
            user.setIsActive(true);
            userRepository.save(user);
        }
        return inactiveUsers;
    }
}