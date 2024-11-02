package org.example.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.example.entities.embeddables.UsersRolesId;

@Entity(name = "Users_Roles")
@Table(name = "Users_Roles")
@Getter()
@Setter()
public class UsersRolesEntity {
    @EmbeddedId()
    private UsersRolesId id;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("userId")
    @JoinColumn(name = "user_id", referencedColumnName = "user_id")
    private UsersEntity usersEntity;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("roleId")
    @JoinColumn(name = "role_id", referencedColumnName = "role_id")
    private RolesEntity rolesEntity;
}
