package org.example.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;
import java.util.UUID;

@Entity(name = "Items")
@Table(name = "Items")
@Getter()
@Setter()
public class ItemsEntity {
    @Id()
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "item_id")
    private UUID itemId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id", referencedColumnName = "category_id")
    private CategoriesEntity categoriesEntity;

    @Column(name = "item_name")
    private String itemName;

    @Column(name = "item_image")
    private String itemImage;

    @Column(name = "description")
    private String description;

    @Column(name = "price")
    private Float price;

    @Column(name = "sale_price")
    private Float salePrice;

    @Column(name = "sale_active")
    private Boolean saleActive;

    @Column(name = "sold")
    private Integer sold;

    @OneToMany(mappedBy = "itemsEntity", cascade = CascadeType.REMOVE, fetch = FetchType.LAZY)
    private Set<CartsEntity> cartsEntity;

    @OneToMany(mappedBy = "itemsEntity", cascade = CascadeType.REMOVE, fetch = FetchType.LAZY)
    private Set<OrderDetailsEntity> orderDetailsEntity;

}
