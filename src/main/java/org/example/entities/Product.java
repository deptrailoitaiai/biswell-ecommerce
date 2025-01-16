package org.example.entities;

import java.util.List;

import jakarta.persistence.CollectionTable;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String pname;
    private String pdesc;
    
    @Column(name = "main_image_path")
    private String mainImagePath;

    // List để lưu các đường dẫn của ảnh
    @ElementCollection
    @CollectionTable(name = "product_pimages", joinColumns = @JoinColumn(name = "product_id"))
    @Column(name = "image_path")
    private List<String> pimages;

}
