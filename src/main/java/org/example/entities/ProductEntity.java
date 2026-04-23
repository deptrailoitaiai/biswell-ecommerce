package org.example.entities;

import java.util.List;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "product")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column
    private String pname;
    @Column(columnDefinition = "TEXT")
    private String pdesc;
//    @Column
//    private String

    @Column(name = "main_image_path")
    private String mainImagePath;

    // List để lưu các đường dẫn của ảnh
    @ElementCollection
    @CollectionTable(name = "product_pimages", joinColumns = @JoinColumn(name = "product_id"))
    @Column(name = "image_path")
    private List<String> pimages;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id", referencedColumnName = "category_id")
    private V2Categories category;

    @Column(name = "is_new", nullable = false, columnDefinition = "TINYINT(1) DEFAULT 0")
    private boolean isNew = false;

    @Column(name = "is_best_seller", nullable = false, columnDefinition = "TINYINT(1) DEFAULT 0")
    private boolean isBestSeller = false;

    @Column(name = "slug", unique = true, nullable = false, length = 300)
    private String slug;

    @Column(name = "meta_title", length = 120)
    private String metaTitle;

    @Column(name = "meta_description", length = 300)
    private String metaDescription;

}
