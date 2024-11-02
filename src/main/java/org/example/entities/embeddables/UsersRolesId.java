package org.example.entities.embeddables;

import jakarta.persistence.Column;
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
public class UsersRolesId implements Serializable {
    private UUID userId;
    private UUID roleId;
}
