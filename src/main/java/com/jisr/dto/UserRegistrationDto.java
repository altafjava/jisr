package com.jisr.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import java.util.Optional;

@Getter
@Setter
public class UserRegistrationDto {

    @NotBlank(message = "Role is mandatory")
    private String role;

    private Optional<String> caregiverRelationship;  // Optional if role is not "caregiver"

    @NotBlank(message = "Username is mandatory")
    @Pattern(regexp = "^[a-zA-Z0-9]+$", message = "Username can contain only alphanumeric characters")
    @Size(min = 5, max = 20, message = "Username must be between 5 and 20 characters")
    private String username;

    @NotBlank(message = "First name is mandatory")
    @Pattern(regexp = "^[A-Za-z]+$", message = "First name can contain only alphabetic characters")
    private String firstName;

    @Pattern(regexp = "^[A-Za-z]*$", message = "Father’s name can contain only alphabetic characters")
    private String fathersName;

    @NotBlank(message = "Last name is mandatory")
    @Pattern(regexp = "^[A-Za-z]+$", message = "Last name can contain only alphabetic characters")
    private String lastName;

    @NotBlank(message = "Email is mandatory")
    @Email(message = "Email should be valid")
    @Pattern(regexp = "^[\\w-\\.]+@(gmail|yahoo)\\.com$", message = "Only public domains (gmail.com, yahoo.com) are allowed")
    private String email;

    @NotBlank(message = "Phone number is mandatory")
    @Pattern(regexp = "^\\+[0-9]{10,15}$", message = "Phone number must be in international format with 12 digits including country code")
    private String phoneNumber;

    @NotBlank(message = "Password is mandatory")
    @Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[!@#$%^&*])[A-Za-z\\d!@#$%^&*]{8,20}$", 
             message = "Password must be 8-20 characters, and include uppercase, lowercase, numbers, and special characters")
    private String password;

}
