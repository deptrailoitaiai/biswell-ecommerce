package org.example.repositories;

import org.example.entities.CategoriesEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository()
public interface CategoryRepository extends JpaRepository<CategoriesEntity, UUID> {
    @Query(value = "SELECT * FROM Categories WHERE category_name = :categoryName")
    Optional<CategoriesEntity> getCategoryByName(@Param("categoryName") String categoryName);
}
