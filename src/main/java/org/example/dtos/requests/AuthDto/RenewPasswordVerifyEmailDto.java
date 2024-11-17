package org.example.dtos.requests.AuthDto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data()
public class RenewPasswordVerifyEmailDto {
    @NotBlank()
    private String email;
}
