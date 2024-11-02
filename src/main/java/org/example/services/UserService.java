package org.example.services;

import org.springframework.stereotype.Service;

@Service
public class UserService {
    public String HandleGetMethod() {
        return "this is a message from user service";
    }
}
