package org.example.services;

import org.example.entities.ArticlesEntity;
import org.example.repositories.ArticleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service()
public class ArticleService {
    @Autowired()
    private ArticleRepository articleRepository;

    public Optional<ArticlesEntity> getArticleById(UUID articleId) {
        return articleRepository.findById(articleId);
    }

    public Optional<ArticlesEntity> getArticleByName(String articleName) {
        return articleRepository.getArticleByName(articleName);
    }

    public ArticlesEntity saveArticle(ArticlesEntity articlesEntity) {
        return articleRepository.save(articlesEntity);
    }

    public void deleteArticle(UUID articleId) {
        articleRepository.deleteById(articleId);
    }
}
