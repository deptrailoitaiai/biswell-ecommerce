package org.example.controllers;

import jakarta.validation.Valid;
import org.example.dtos.requests.ArticleDtos.CreateArticleRequestDto;
import org.example.dtos.requests.ArticleDtos.DeleteArticleRequestDto;
import org.example.dtos.requests.ArticleDtos.UpdateArticleRequestDto;
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
    public ArticlesEntity createArticle(@Valid() @RequestBody() CreateArticleRequestDto createArticleRequestDto) {
        ArticlesEntity articlesEntity = entityNonNull.map(createArticleRequestDto, ArticlesEntity.class);

        return articleService.saveArticle(articlesEntity);
    }

    @PatchMapping()
    public ArticlesEntity updateArticle(@Valid() @RequestBody() UpdateArticleRequestDto updateArticleRequestDto) {
        ArticlesEntity articlesEntity = entityNonNull.map(updateArticleRequestDto, ArticlesEntity.class);

        return articleService.saveArticle(articlesEntity);
    }

    @DeleteMapping()
    public void deleteArticle(@Valid() @RequestBody() DeleteArticleRequestDto deleteArticleRequestDto) {
        articleService.deleteArticle(deleteArticleRequestDto.getArticleId());
    }
}