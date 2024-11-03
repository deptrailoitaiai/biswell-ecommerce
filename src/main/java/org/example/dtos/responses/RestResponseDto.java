package org.example.dtos.responses;

import lombok.Data;

@Data()
public class RestResponseDto<T> {
    private boolean sucsess;
    private String message;
    private T data;
}
