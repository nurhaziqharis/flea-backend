package com.flea.flea.admin.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/api/v1/admin")
public class AdminPingController {

    @GetMapping("/ping")
    @Secured("ROLE_Admin")
    public ResponseEntity<Map<String, String>> ping(Authentication authentication) {
        return ResponseEntity.ok(Map.of(
                "message", "pong",
                "principal", authentication.getName()
        ));
    }
}
