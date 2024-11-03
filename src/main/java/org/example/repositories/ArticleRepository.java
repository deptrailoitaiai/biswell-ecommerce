package org.example.repositories;

import org.example.entities.ArticlesEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository()
public interface ArticleRepository extends JpaRepository<ArticlesEntity, UUID> {
}
