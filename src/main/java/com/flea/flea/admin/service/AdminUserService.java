package com.flea.flea.admin.service;

import com.flea.flea.admin.dto.AssignRolesRequest;
import com.flea.flea.admin.dto.CreateUserRequest;
import com.flea.flea.admin.dto.UserResponse;
import com.flea.flea.domain.entity.Role;
import com.flea.flea.domain.entity.User;
import com.flea.flea.domain.repository.RoleRepository;
import com.flea.flea.domain.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AdminUserService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    @Transactional(readOnly = true)
    public List<UserResponse> listUsers() {
        return userRepository.findAll().stream()
                .map(this::toResponse)
                .toList();
    }

    @Transactional
    public UserResponse createUser(CreateUserRequest request) {
        if (userRepository.existsByEmail(request.getEmail())) {
            throw new IllegalStateException("Email already in use: " + request.getEmail());
        }
        if (userRepository.existsByUsername(request.getUsername())) {
            throw new IllegalStateException("Username already taken: " + request.getUsername());
        }

        Set<Role> resolved = resolveRoles(request.getRoles());

        User user = User.builder()
                .email(request.getEmail())
                .username(request.getUsername())
                .password(passwordEncoder.encode(request.getPassword()))
                .roles(resolved)
                .build();

        return toResponse(userRepository.save(user));
    }

    @Transactional
    public UserResponse assignRoles(UUID userId, AssignRolesRequest request) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalStateException("User not found: " + userId));
        user.setRoles(resolveRoles(request.getRoles()));
        return toResponse(userRepository.save(user));
    }

    @Transactional
    public void deleteUser(UUID userId) {
        if (!userRepository.existsById(userId)) {
            throw new IllegalStateException("User not found: " + userId);
        }
        userRepository.deleteById(userId);
    }

    private Set<Role> resolveRoles(Set<String> names) {
        Set<Role> roles = new HashSet<>();
        for (String name : names) {
            Role role = roleRepository.findByName(name)
                    .orElseThrow(() -> new IllegalStateException("Role not found: " + name));
            roles.add(role);
        }
        return roles;
    }

    private UserResponse toResponse(User user) {
        return UserResponse.builder()
                .id(user.getId())
                .email(user.getUsername())
                .username(user.getDisplayUsername())
                .roles(user.getRoles().stream().map(Role::getName).collect(Collectors.toSet()))
                .createdAt(user.getCreatedAt())
                .updatedAt(user.getUpdatedAt())
                .build();
    }
}
