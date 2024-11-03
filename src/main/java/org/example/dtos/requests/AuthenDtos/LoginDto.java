package org.example.dtos.requests.AuthenDtos;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
public class LoginDto {
    @NotBlank()
    private String userName;

    @NotBlank()
    private String password;
}
