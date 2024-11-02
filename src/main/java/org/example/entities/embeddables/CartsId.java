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
public class CartsId implements Serializable {
    private UUID userId;
    private UUID itemId;
}
