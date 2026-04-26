package com.flea.flea.admin.service;

import com.flea.flea.admin.dto.CreateRoleRequest;
import com.flea.flea.admin.dto.RoleResponse;
import com.flea.flea.domain.entity.Role;
import com.flea.flea.domain.repository.RoleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AdminRoleService {

    private final RoleRepository roleRepository;

    @Transactional(readOnly = true)
    public List<RoleResponse> listRoles() {
        return roleRepository.findAll().stream()
                .map(r -> RoleResponse.builder().id(r.getId()).name(r.getName()).build())
                .toList();
    }

    @Transactional
    public RoleResponse createRole(CreateRoleRequest request) {
        if (roleRepository.findByName(request.getName()).isPresent()) {
            throw new IllegalStateException("Role already exists: " + request.getName());
        }
        Role saved = roleRepository.save(Role.builder().name(request.getName()).build());
        return RoleResponse.builder().id(saved.getId()).name(saved.getName()).build();
    }
}
