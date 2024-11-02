package org.example.entities.embeddables;

import jakarta.persistence.Embeddable;
import lombok.*;

import java.util.UUID;

@Embeddable()
@EqualsAndHashCode()
@Getter()
@Setter()
@AllArgsConstructor()
@NoArgsConstructor()
public class OrderDetailsId {
    private UUID orderId;
    private UUID itemId;
}
