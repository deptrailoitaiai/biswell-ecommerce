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
    @Query(value = "SELECT c FROM Categories c WHERE c.categoryName = :categoryName")
    Optional<CategoriesEntity> getCategoryByName(@Param("categoryName") String categoryName);

    @Query("SELECT CASE WHEN COUNT(c) > 0 THEN true ELSE false END FROM Categories c WHERE c.categoryName = :categoryName")
    boolean existsByName(String categoryName);
}
