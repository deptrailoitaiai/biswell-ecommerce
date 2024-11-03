package org.example.repositories;

import org.example.entities.PermissionsEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface PermissionRepository extends JpaRepository<PermissionsEntity, UUID> {
}
