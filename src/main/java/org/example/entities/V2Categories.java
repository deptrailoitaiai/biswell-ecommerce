package org.example.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "v2_categories")
@Getter()
@Setter()
public class V2Categories {
    @Id()
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "category_id")
    private Long categoryId;

    @Column(name = "category_name")
    private String categoryName;

    @Column(name = "description")
    private String description;

    @Column(name = "category_image")
    private String categoryImage;

    @OneToMany(mappedBy = "category", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<ProductEntity> productEntity;
}

