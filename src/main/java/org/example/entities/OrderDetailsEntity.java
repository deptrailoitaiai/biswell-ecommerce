package org.example.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.example.entities.embeddables.OrderDetailsId;

@Entity(name = "OrderDetails")
@Table(name = "OrderDetails")
@Getter()
@Setter()
public class OrderDetailsEntity {
    @EmbeddedId()
    private OrderDetailsId id;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("orderId")
    @JoinColumn(name = "order_id", referencedColumnName = "order_id")
    private OrdersEntity ordersEntity;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("itemId")
    @JoinColumn(name = "item_id", referencedColumnName = "item_id")
    private ItemsEntity itemsEntity;

    @Column(name = "quantity", nullable = false)
    private Integer quantity;
}
