package org.example.repositories;

import org.apache.catalina.User;
import org.example.entities.UsersEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository()
public interface UserRepository extends JpaRepository<UsersEntity, UUID> {
    @Query(value = "SELECT u FROM Users u WHERE u.email = :email")
    UsersEntity getUserByEmail(@Param("email") String email);

    @Query(value = "SELECT u FROM Users u WHERE u.userName = :userName")
    UsersEntity getUserByUserName(@Param("userName") String userName);
}
