package org.example.dtos.responses.AuthDtos;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data()
@NoArgsConstructor()
@AllArgsConstructor()
@JsonInclude(JsonInclude.Include.NON_NULL)
public class LoginResponseDto {
    private String jwtToken;
}
