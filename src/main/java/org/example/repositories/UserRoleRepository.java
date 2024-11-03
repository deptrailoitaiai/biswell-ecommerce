package org.example.repositories;

import org.example.entities.UsersRolesEntity;
import org.example.entities.embeddables.UsersRolesId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository()
public interface UserRoleRepository extends JpaRepository<UsersRolesEntity, UsersRolesId> {
}
