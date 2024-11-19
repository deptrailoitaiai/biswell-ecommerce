package org.example.repositories;

import org.example.entities.ArticlesEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository()
public interface ArticleRepository extends JpaRepository<ArticlesEntity, UUID> {
    @Query(value = "SELECT * FROM Articles WHERE article_name = :articleName")
    Optional<ArticlesEntity> getArticleByName(@Param("articleName") String articleName);
}
