package org.example.config;

import org.example.services.UserService;
import org.example.utils.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

@Configuration()
@EnableWebSecurity()
public class SecurityConfig {
    @Autowired()
    private UserService userService;

    @Autowired()
    private JwtUtil jwtUtil;
}
