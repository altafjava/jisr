package com.jisr.security;

import java.util.Optional;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import com.jisr.entity.User;
import com.jisr.repository.UserRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String emailOrPhone) throws UsernameNotFoundException {
        Optional<User> userOptional = emailOrPhone.contains("@")
            ? userRepository.findByEmail(emailOrPhone)
            : userRepository.findByPhoneNumber(emailOrPhone);
        if (userOptional.isEmpty()) {
            throw new UsernameNotFoundException("User not found with email or phone: " + emailOrPhone);
        }
        return new UserDetailsImpl(userOptional.get());
    }
}
