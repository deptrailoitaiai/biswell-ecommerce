package org.example.dtos.requests.UserDtos;

import jakarta.validation.constraints.NotBlank;

import java.util.UUID;

public class DeleteUserRequestDto {
    @NotBlank()
    private UUID userId;
}
