package org.example.services;

import org.example.entities.ArticlesEntity;
import org.example.exceptions.ArticleExceptions.ArticleExistedException;
import org.example.exceptions.ArticleExceptions.ArticleNotExistException;
import org.example.repositories.ArticleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service()
public class ArticleService {
    @Autowired()
    private ArticleRepository articleRepository;

    public ArticlesEntity getArticleById(UUID articleId) {
        return articleRepository.findById(articleId)
                .orElseThrow(() -> new ArticleNotExistException("Article '" + articleId + "' not exist"));
    }

    public ArticlesEntity getArticleByName(String articleName) {
        return articleRepository.getArticleByName(articleName)
                .orElseThrow(() -> new ArticleNotExistException("Article '" + articleName + "' not exist"));
    }

    public ArticlesEntity createArticle(ArticlesEntity articlesEntity) {
        if(articleRepository.existsByName(articlesEntity.getArticleName())) {
            throw new ArticleExistedException("Article '" + articlesEntity.getArticleName() + "' existed");
        }

        return articleRepository.save(articlesEntity);
    }

    public ArticlesEntity updateArticle(ArticlesEntity articlesEntity) {
        if(articleRepository.existsById(articlesEntity.getArticleId())) {
            return articleRepository.save(articlesEntity);
        }

        throw new ArticleNotExistException("Article '" + articlesEntity.getArticleName() + "' not exist");
    }

    public void deleteArticle(UUID articleId) {
        if(articleRepository.existsById(articleId)) {
            articleRepository.deleteById(articleId);
            return;
        }

        throw new ArticleNotExistException("Article '" + articleId + "' not exist");
    }
}
