package com.flea.flea.mapper;

import com.flea.flea.admin.dto.CreateUserResponse;
import com.flea.flea.admin.dto.UserResponse;
import com.flea.flea.domain.entity.Role;
import com.flea.flea.domain.entity.User;
import com.flea.flea.domain.entity.Wallet;
import com.flea.flea.dto.response.WalletResponse;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class UserMapper {

    public UserResponse toUserResponse(User user) {
        return UserResponse.builder()
                .id(user.getId())
                .email(user.getUsername())
                .username(user.getDisplayUsername())
                .roles(user.getRoles().stream().map(Role::getName).collect(Collectors.toSet()))
                .createdAt(user.getCreatedAt())
                .updatedAt(user.getUpdatedAt())
                .build();
    }

    public CreateUserResponse createUserResponse(User user, Wallet wallet) {
        WalletResponse walletResponse = WalletResponse.builder()
                .id(wallet.getId())
                .amount(wallet.getBalance())
                .build();

        return CreateUserResponse.builder()
                .id(user.getId())
                .email(user.getUsername())
                .username(user.getDisplayUsername())
                .roles(user.getRoles().stream().map(Role::getName).collect(Collectors.toSet()))
                .createdAt(user.getCreatedAt())
                .updatedAt(user.getUpdatedAt())
                .wallet(walletResponse)
                .build();
    }
}
