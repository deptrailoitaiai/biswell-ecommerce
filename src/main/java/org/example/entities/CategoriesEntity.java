package org.example.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;
import java.util.UUID;

@Entity(name = "Categories")
@Table(name = "Categories")
@Getter()
@Setter()
public class CategoriesEntity {
    @Id()
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "category_id")
    private UUID categoryId;

    @Column(name = "category_name")
    private String categoryName;

    @Column(name = "description")
    private String description;

    @Column(name = "category_image")
    private String categoryImage;

    @OneToMany(mappedBy = "categoriesEntity", cascade = CascadeType.REMOVE, fetch = FetchType.LAZY)
    private Set<ItemsEntity> itemsEntity;
}
