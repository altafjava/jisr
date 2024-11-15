package com.jisr.service;

import java.util.EnumSet;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.jisr.entity.RoleEnum;
import com.jisr.entity.User;
import com.jisr.repository.UserRepository;
import com.jisr.util.EmailService;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class NotificationService {

    private final UserRepository userRepository;
    private final EmailService emailService;

    @Transactional
    public void notifyPatientsAndCaregivers() {
        List<String> roleNames = EnumSet.of(RoleEnum.PATIENT, RoleEnum.CAREGIVER).stream()
                                        .map(RoleEnum::getRoleName)
                                        .collect(Collectors.toList());
        notifyAndActivateUsers(roleNames);
    }

    @Transactional
    public void notifyPatients() {
        List<String> roleNames = EnumSet.of(RoleEnum.PATIENT).stream()
                                        .map(RoleEnum::getRoleName)
                                        .collect(Collectors.toList());
        notifyAndActivateUsers(roleNames);
    }

    @Transactional
    public void notifyHealthcareProviders() {
        List<String> roleNames = EnumSet.of(RoleEnum.HEALTHCARE_PROVIDER).stream()
                                        .map(RoleEnum::getRoleName)
                                        .collect(Collectors.toList());
        notifyAndActivateUsers(roleNames);
    }
    
    private void notifyAndActivateUsers(List<String> roleNames) {
        List<User> inactiveUsers = userRepository.findInactiveUsersByRoles(roleNames);
        String subject = "Your Registration is Now Open!";
        for (User user : inactiveUsers) {
            user.setIsActive(true);
            userRepository.save(user);
            String body = "Hello " + user.getUsername() + ",\n\nYour registration has been successfully activated. You can now login to your account.";
            emailService.sendEmail(user.getEmail(), subject, body);
        }
    }
}
