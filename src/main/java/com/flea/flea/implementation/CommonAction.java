package com.flea.flea.implementation;

import com.flea.flea.domain.entity.User;
import com.flea.flea.domain.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CommonAction {

    private final UserRepository userRepository;

    User getCurrentUser(){
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        User poolAdminUser = userRepository.findByEmail(email)
                .orElseThrow(() -> new IllegalStateException("Authenticated user not found"));
        return poolAdminUser;
    }

}
