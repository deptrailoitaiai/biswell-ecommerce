package org.example.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.example.constants.Enum;

import java.util.Set;
import java.util.UUID;

@Entity(name = "Permissions")
@Table(name = "Permissions")
@Getter
@Setter
public class PermissionsEntity {
    @Id()
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "permission_id", columnDefinition = "binary(16)")
    private UUID permissionId;

    @Column(name = "action", nullable = false)
    @Enumerated(EnumType.STRING)
    private Enum.ActionEnum action;

    @Column(name = "module", nullable = false)
    @Enumerated(EnumType.STRING)
    private Enum.ModuleEnum module;

    @OneToMany(mappedBy = "permissionsEntity", cascade = CascadeType.REMOVE, fetch = FetchType.LAZY)
    private Set<RolesPermissionsEntity> rolesPermissionsEntity;
}
