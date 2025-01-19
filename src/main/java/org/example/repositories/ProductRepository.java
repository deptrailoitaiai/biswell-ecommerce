package org.example.repositories;

import java.util.List;

import org.example.entities.ProductEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<ProductEntity, Long> {

    List<ProductEntity> findTop8ByOrderById();
    Page<ProductEntity> findByCategory_CategoryId(Long categoryId, Pageable pageable);
}