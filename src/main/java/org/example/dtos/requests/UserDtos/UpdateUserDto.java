package org.example.dtos.requests.UserDtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

import java.util.UUID;

@Data()
public class UpdateUserDto {
    @NotBlank()
    private UUID userId;

    private String password;

    private String address;

    @Pattern(regexp = "\\d{10}")
    private String phone;
}
