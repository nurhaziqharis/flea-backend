package com.flea.flea.implementation;

import com.flea.flea.domain.entity.User;
import com.flea.flea.domain.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@RequiredArgsConstructor
public class CommonAction {

    private final UserRepository userRepository;

    public User getCurrentUser(){
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        User currentUser = userRepository.findByEmail(email)
                .orElseThrow(() -> new IllegalStateException("Authenticated user not found"));
        return currentUser;
    }

    public UUID convertStringToUUID(String uuidToConvert){
        try{
            return UUID.fromString(uuidToConvert);
        } catch (IllegalArgumentException error) {
            throw new RuntimeException(error.getMessage());
        }
    }

}
