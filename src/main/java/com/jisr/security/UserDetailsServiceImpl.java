package com.jisr.security;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import com.jisr.entity.Patient;
import com.jisr.repository.PatientRepository;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    private final PatientRepository patientRepository;

    public UserDetailsServiceImpl(PatientRepository userRepository) {
        this.patientRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String emailOrPhone) throws UsernameNotFoundException {
        Patient patient = emailOrPhone.contains("@")
            ? patientRepository.findByEmail(emailOrPhone)
            : patientRepository.findByPhoneNumber(emailOrPhone);
        if (patient == null) {
            throw new UsernameNotFoundException("User not found with email or phone: " + emailOrPhone);
        }
        return new UserDetailsImpl(patient);
    }
}
