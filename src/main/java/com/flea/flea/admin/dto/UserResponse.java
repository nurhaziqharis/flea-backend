package com.flea.flea.admin.dto;

import lombok.Builder;
import lombok.Data;

import java.time.Instant;
import java.util.Set;
import java.util.UUID;

@Data
@Builder
public class UserResponse {
    private UUID id;
    private String email;
    private String username;
    private Set<String> roles;
    private Instant createdAt;
    private Instant updatedAt;
}
