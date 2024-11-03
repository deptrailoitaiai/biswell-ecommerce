package org.example.dtos.requests.AuthenDtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data()
public class RenewPasswordVerifyCodeDto {
    @NotBlank()
    @Pattern(regexp = "\\d{6}", message = "require 6 digit number")
    private String sixDigitNumber;
}
