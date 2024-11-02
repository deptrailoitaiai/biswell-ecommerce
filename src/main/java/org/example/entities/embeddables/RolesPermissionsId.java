package org.example.entities.embeddables;

import jakarta.persistence.Embeddable;
import lombok.*;

import java.io.Serializable;
import java.util.UUID;

@Embeddable()
@EqualsAndHashCode()
@Getter()
@Setter()
@AllArgsConstructor()
@NoArgsConstructor()
public class RolesPermissionsId implements Serializable {
    private UUID roleId;
    private UUID permissionId;
}
