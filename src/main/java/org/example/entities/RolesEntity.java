package org.example.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.example.constants.Enum;

import java.util.Set;
import java.util.UUID;

@Entity(name = "Roles")
@Table(name = "Roles")
@Getter()
@Setter()
public class RolesEntity {
    @Id()
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "role_id")
    private UUID roleId;

    @Column(name = "role_name", nullable = false)
    @Enumerated(EnumType.STRING)
    private Enum.RoleEnum roleName;

    @OneToMany(mappedBy = "rolesEntity", cascade = CascadeType.REMOVE, fetch = FetchType.LAZY)
    private Set<UsersRolesEntity> usersRolesEntity;

    @OneToMany(mappedBy = "rolesEntity", cascade = CascadeType.REMOVE, fetch = FetchType.LAZY)
    private Set<RolesPermissionsEntity> rolesPermissionsEntity;
}

