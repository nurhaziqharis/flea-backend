package com.flea.flea.controllers;

import com.flea.flea.auth.UserRoleConstant;
import com.flea.flea.dto.response.ParticipatorResponseBase;
import com.flea.flea.service.ParticipatorService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/participator")
public class ParticipatorController {

    private ParticipatorService participatorService;

    @GetMapping
    @Secured({
            "ROLE_" + UserRoleConstant.USER,
            "ROLE_" + UserRoleConstant.ADMINISTRATOR
    })
    public ResponseEntity<Page<ParticipatorResponseBase>> getAllParticipator(
            @RequestParam(defaultValue = "0") int start,
            @RequestParam(defaultValue = "20") int off,
            @RequestParam(required = false) List<String> filters
    ){
        return ResponseEntity.ok(participatorService.getAllParticipator(start, off, filters));
    }

    @GetMapping("/{id}")
    @Secured({
            "ROLE_" + UserRoleConstant.USER,
            "ROLE_" + UserRoleConstant.ADMINISTRATOR
    })
    public ResponseEntity<ParticipatorResponseBase> getParticipator(
            @RequestParam(required = true) String id
    ){
        return ResponseEntity.ok(participatorService.getParticipator(id));
    }
}
