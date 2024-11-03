package org.example.dtos.requests.AuthenDtos;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data()
public class RenewPasswordVerifyEmailDto {
    @NotBlank()
    private String email;
}
