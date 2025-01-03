package org.example.services;

import org.example.entities.UsersEntity;
import org.example.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class UserService {
    @Autowired()
    private UserRepository userRepository;

    public Optional<UsersEntity> getUserById(UUID userId) {
        return userRepository.findById(userId);
    };

    public UsersEntity saveUser(UsersEntity usersEntity) {
        return userRepository.save(usersEntity);
    }

    public void deleteUser(UUID userId) {
        userRepository.deleteById(userId);
    }

    public UsersEntity getUserByUserName(String userName) {
        return userRepository.getUserByUserName(userName);
    }

    public UsersEntity getUserByEmail(String email) {
        return userRepository.getUserByEmail(email);
    }



}
