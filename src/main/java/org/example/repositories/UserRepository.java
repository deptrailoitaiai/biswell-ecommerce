package org.example.repositories;

import org.example.entities.UsersEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository()
public interface UserRepository extends JpaRepository<UsersEntity, UUID> {
}
