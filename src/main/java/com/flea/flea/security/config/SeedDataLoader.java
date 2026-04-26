package com.flea.flea.security.config;

import com.flea.flea.domain.entity.Role;
import com.flea.flea.domain.entity.User;
import com.flea.flea.domain.repository.RoleRepository;
import com.flea.flea.domain.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;

@Component
@RequiredArgsConstructor
public class SeedDataLoader implements CommandLineRunner {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    @Transactional
    public void run(String... args) {
        if (userRepository.findByEmail("admin@local").isEmpty()) {
            Role adminRole = roleRepository.findByName("Admin")
                    .orElseGet(() -> roleRepository.save(Role.builder().name("Admin").build()));

            User admin = User.builder()
                    .email("admin@local")
                    .username("admin")
                    .password(passwordEncoder.encode("1"))
                    .roles(Set.of(adminRole))
                    .build();

            userRepository.save(admin);
            System.out.println("Seeded default admin user: admin@local / 1");
        }
    }
}
