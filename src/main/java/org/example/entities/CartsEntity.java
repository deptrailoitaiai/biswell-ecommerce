package org.example.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.example.entities.embeddables.CartsId;

@Entity(name = "Carts")
@Table(name = "Carts")
@Getter()
@Setter()
public class CartsEntity {
    @EmbeddedId()
    private CartsId id;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("userId")
    @JoinColumn(name = "user_id", referencedColumnName = "user_id")
    private UsersEntity usersEntity;


    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("itemId")
    @JoinColumn(name = "item_id", referencedColumnName = "item_id")
    private ItemsEntity itemsEntity;

    @Column(name = "quantity", nullable = false)
    private Integer quantity;
}
