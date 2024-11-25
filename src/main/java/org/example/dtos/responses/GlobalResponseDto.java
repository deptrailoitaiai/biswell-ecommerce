package org.example.dtos.responses;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data()
@AllArgsConstructor()
@JsonInclude(JsonInclude.Include.ALWAYS)
public class GlobalResponseDto<T> {
    private boolean success;
    private String message;
    private T data;

    public GlobalResponseDto(boolean success, String message) {
        this.success = success;
        this.message = message;
    }
}
