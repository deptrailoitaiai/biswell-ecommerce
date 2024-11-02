package org.example.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.example.constants.Enum;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.Set;
import java.util.UUID;

@Entity(name = "Orders")
@Table(name = "Orders")
@Getter()
@Setter()
public class OrdersEntity {
    @Id()
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "order_id")
    private UUID orderId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", referencedColumnName = "user_id")
    private UsersEntity usersEntity;

    @Column(name = "create_at")
    @CreationTimestamp()
    private LocalDateTime createAt;

    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    private Enum.OrderStatusEnum status;

    @Column(name = "address", nullable = false)
    private String address;

    @OneToMany(mappedBy = "ordersEntity", cascade = CascadeType.REMOVE, fetch = FetchType.LAZY)
    private Set<OrderDetailsEntity> orderDetailsEntity;
}
