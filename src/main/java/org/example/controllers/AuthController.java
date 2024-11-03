package org.example.controllers;

import jakarta.validation.Valid;
import org.example.dtos.requests.AuthenDtos.LoginDto;
import org.example.dtos.responses.RestResponseDto;
import org.springframework.web.bind.annotation.*;

@RestController()
@RequestMapping("Auth")
public class AuthController {

    @PostMapping("Login")
    public RestResponseDto<?> AuthLoginPost(@Valid() @RequestBody() LoginDto loginDto) {

    }
}
