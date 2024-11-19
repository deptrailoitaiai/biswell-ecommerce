package org.example.dtos.requests.AuthDtos;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data()
public class SignUpDto {
    @NotBlank()
    private String userName;

    @NotBlank()
    private String password;

    @NotBlank()
    private String email;
}
