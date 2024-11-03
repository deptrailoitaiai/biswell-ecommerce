package org.example.repositories;

import org.example.entities.RolesPermissionsEntity;
import org.example.entities.embeddables.RolesPermissionsId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository()
public interface RolePermissionRepository extends JpaRepository<RolesPermissionsEntity, RolesPermissionsId> {
}
