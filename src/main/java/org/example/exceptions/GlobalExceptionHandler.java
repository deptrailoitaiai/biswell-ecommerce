package org.example.exceptions;

import org.example.dtos.responses.GlobalResponseDto;
import org.example.exceptions.RuntimeExceptions.ExistedException;
import org.example.exceptions.RuntimeExceptions.NotExistsException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice()
public class GlobalExceptionHandler {
    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public ResponseEntity<GlobalResponseDto<String>> handleMethodNotAllowed(HttpRequestMethodNotSupportedException e){
        GlobalResponseDto<String> response = new GlobalResponseDto<>(
                false,
                "HTTP method not supported for this endpoint."
        );

        return ResponseEntity.status(HttpStatus.METHOD_NOT_ALLOWED).body(response);
    }

    @ExceptionHandler()
    public ResponseEntity<GlobalResponseDto<String>> handleAlbumNotExistException (NotExistsException e) {
        GlobalResponseDto<String> response = new GlobalResponseDto<>(
                false,
                e.getMessage()
        );

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
    }

    @ExceptionHandler()
    public ResponseEntity<GlobalResponseDto<String>> handleAlbumExistedException(ExistedException e) {
        GlobalResponseDto<String> response = new GlobalResponseDto<>(
                false,
                e.getMessage()
        );

        return ResponseEntity.status(HttpStatus.CONFLICT).body(response);
    }
}
