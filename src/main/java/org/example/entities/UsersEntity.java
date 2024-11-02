package org.example.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;
import java.util.UUID;

@Entity(name = "Users")
@Table(name = "Users")
@Getter()
@Setter()
public class UsersEntity {
    @Id()
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "user_id")
    private UUID userId;

    @Column(name = "email", unique = true, nullable = false)
    private String email;

    @Column(name = "user_name", nullable = false)
    private String userName;

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "address")
    private String address;

    @Column(name = "phone", columnDefinition = "VARCHAR(10)")
    private String phone;

    @OneToMany(mappedBy = "usersEntity", cascade = CascadeType.REMOVE, fetch = FetchType.LAZY)
    private Set<UsersRolesEntity> usersRolesEntity;

    @OneToMany(mappedBy = "usersEntity", cascade = CascadeType.REMOVE, fetch = FetchType.LAZY)
    private Set<OrdersEntity> ordersEntity;

    @OneToMany(mappedBy = "usersEntity", cascade = CascadeType.REMOVE, fetch = FetchType.LAZY)
    private Set<CartsEntity> cartsEntity;
}
