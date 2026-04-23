package org.example.repositories;

import java.util.List;

import org.example.entities.ProductEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ProductRepository extends JpaRepository<ProductEntity, Long> {

    List<ProductEntity> findTop8ByOrderByIdDesc();
    java.util.Optional<ProductEntity> findBySlug(String slug);
    boolean existsBySlug(String slug);
    boolean existsBySlugAndIdNot(String slug, Long id);
    List<ProductEntity> findByIsNewTrueOrderByIdDesc();
    List<ProductEntity> findByIsBestSellerTrueOrderByIdDesc();
    Page<ProductEntity> findByCategory_CategoryId(Long categoryId, Pageable pageable);

    @Query("SELECT p FROM ProductEntity p WHERE p.pname LIKE %:search%")
    Page<ProductEntity> findByPnameLike(@Param("search") String search, Pageable pageable);
}