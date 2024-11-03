package org.example.repositories;

import org.example.entities.RolesEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface RoleRepository extends JpaRepository<RolesEntity, UUID> {
}
