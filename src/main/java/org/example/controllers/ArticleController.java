package org.example.controllers;

import jakarta.validation.Valid;
import org.example.dtos.requests.ArticleDtos.CreateArticleDto;
import org.example.dtos.requests.ArticleDtos.DeleteArticleDto;
import org.example.dtos.requests.ArticleDtos.UpdateArticleDto;
import org.example.entities.ArticlesEntity;
import org.example.services.ArticleService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.UUID;

@RestController()
@RequestMapping("/article")
public class ArticleController {
    @Autowired()
    private ArticleService articleService;

    @Autowired()
    private ModelMapper entityNonNull;

    @GetMapping("/id/{id}")
    public Optional<ArticlesEntity> getArticleById(@PathVariable("id") UUID id) {
        return articleService.getArticleById(id);
    }

    @GetMapping("/name/{name}")
    public Optional<ArticlesEntity> getArticleByName(@PathVariable("name") String name) {
        return articleService.getArticleByName(name);
    }

    @PostMapping()
    public ArticlesEntity createArticle(@Valid() @RequestBody()CreateArticleDto createArticleDto) {
        ArticlesEntity articlesEntity = entityNonNull.map(createArticleDto, ArticlesEntity.class);

        return articleService.saveArticle(articlesEntity);
    }

    @PatchMapping()
    public ArticlesEntity updateArticle(@Valid() @RequestBody()UpdateArticleDto updateArticleDto) {
        ArticlesEntity articlesEntity = entityNonNull.map(updateArticleDto, ArticlesEntity.class);

        return articleService.saveArticle(articlesEntity);
    }

    @DeleteMapping()
    public void deleteArticle(@Valid() @RequestBody()DeleteArticleDto deleteArticleDto) {
        articleService.deleteArticle(deleteArticleDto.getArticleId());
    }
}