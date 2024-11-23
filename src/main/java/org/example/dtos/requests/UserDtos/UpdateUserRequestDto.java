package org.example.dtos.requests.UserDtos;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

import java.util.UUID;

@Data()
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UpdateUserRequestDto {
    @NotBlank()
    private UUID userId;

    private String password;

    private String address;

    @Pattern(regexp = "\\d{10}")
    private String phone;
}
