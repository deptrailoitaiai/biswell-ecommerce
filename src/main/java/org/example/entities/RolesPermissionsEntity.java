package org.example.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.example.entities.embeddables.RolesPermissionsId;

@Entity(name = "Roles_Permissions")
@Table(name = "Roles_Permissions")
@Getter()
@Setter()
public class RolesPermissionsEntity {
    @EmbeddedId()
    private RolesPermissionsId id;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("roleId")
    @JoinColumn(name = "role_id", referencedColumnName = "role_id")
    private RolesEntity rolesEntity;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("permissionId")
    @JoinColumn(name = "permission_id", referencedColumnName = "permission_id")
    private PermissionsEntity permissionsEntity;
}


