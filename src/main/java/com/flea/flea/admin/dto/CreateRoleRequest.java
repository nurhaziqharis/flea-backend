package com.flea.flea.admin.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class CreateRoleRequest {

    @NotBlank
    @Size(min = 2, max = 50)
    private String name;
}
