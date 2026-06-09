package com.flea.flea.dto.request;

import com.flea.flea.enumeration.Gender;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Setter
@Getter
public class UserRequest {
    private String email;
    private String username;
    private String password;
    private String fullname;
    private String identityNumber;
    private Gender gender;
    private String phoneNumber;
    private LocalDate birthDate;
    private LocalDateTime lastLogin;
    private Boolean isVerified = false;
    private Boolean isBanned = false;
}
