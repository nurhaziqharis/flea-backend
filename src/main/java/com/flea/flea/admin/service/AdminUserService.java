package com.flea.flea.admin.service;

import com.flea.flea.admin.dto.AssignRolesRequest;
import com.flea.flea.admin.dto.CreateUserRequest;
import com.flea.flea.admin.dto.CreateUserResponse;
import com.flea.flea.admin.dto.UserResponse;
import com.flea.flea.domain.entity.Role;
import com.flea.flea.domain.entity.User;
import com.flea.flea.domain.entity.Wallet;
import com.flea.flea.domain.repository.RoleRepository;
import com.flea.flea.domain.repository.UserRepository;
import com.flea.flea.domain.repository.WalletRepository;
import com.flea.flea.mapper.UserMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AdminUserService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;
    private final WalletRepository walletRepository;
    private final UserMapper userMapper;

    @Transactional(readOnly = true)
    public List<UserResponse> listUsers() {
        return userRepository.findAll().stream()
                .map(userMapper::toUserResponse)
                .toList();
    }

    @Transactional
    public CreateUserResponse createUser(CreateUserRequest request) {
        if (userRepository.existsByEmail(request.getEmail())) {
            throw new IllegalStateException("Email already in use: " + request.getEmail());
        }
        if (userRepository.existsByUsername(request.getUsername())) {
            throw new IllegalStateException("Username already taken: " + request.getUsername());
        }

        Set<Role> resolved = resolveRoles(request.getRoles());

        User newUser = User.builder()
                .email(request.getEmail())
                .username(request.getUsername())
                .password(passwordEncoder.encode(request.getPassword()))
                .roles(resolved)
                .build();

        Wallet newWallet = Wallet.builder()
                .username(newUser.getUsername())
                .owner(newUser)
                .build();

        userRepository.save(newUser);
        walletRepository.save(newWallet);

        return userMapper.createUserResponse(newUser, newWallet);
    }

    @Transactional
    public UserResponse assignRoles(UUID userId, AssignRolesRequest request) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalStateException("User not found: " + userId));
        user.setRoles(resolveRoles(request.getRoles()));
        return userMapper.toUserResponse(user);
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


}
