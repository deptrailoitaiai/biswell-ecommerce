package org.example.repositories;

import org.example.entities.CategoriesEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository()
public interface CategoryRepository extends JpaRepository<CategoriesEntity, UUID> {
}
