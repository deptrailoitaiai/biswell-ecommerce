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
    @Query(value = "SELECT a FROM Articles a WHERE a.articleName = :articleName")
    Optional<ArticlesEntity> getArticleByName(@Param("articleName") String articleName);

    @Query("SELECT CASE WHEN COUNT(a) > 0 THEN true ELSE false END FROM Articles a WHERE a.articleName = :articleName")
    boolean existsByName(String articleName);
}
