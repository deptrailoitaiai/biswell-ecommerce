package org.example.dtos.responses;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;

@Data()
@NoArgsConstructor()
@AllArgsConstructor()
@JsonInclude(JsonInclude.Include.NON_NULL)
public class RestResponseDto<T> {
    private boolean sucsess;
    private String message;
    private T data;
}
