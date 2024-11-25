package org.example.controllers;

import jakarta.validation.Valid;
import org.example.dtos.requests.ArticleDtos.CreateArticleRequestDto;
import org.example.dtos.requests.ArticleDtos.DeleteArticleRequestDto;
import org.example.dtos.requests.ArticleDtos.UpdateArticleRequestDto;
import org.example.dtos.responses.ArticleDtos.CreateArticleResponseDto;
import org.example.dtos.responses.ArticleDtos.GetArticleResponseDto;
import org.example.dtos.responses.ArticleDtos.UpdateArticleResponseDto;
import org.example.dtos.responses.GlobalResponseDto;
import org.example.entities.ArticlesEntity;
import org.example.services.ArticleService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.UUID;

@RestController()
@RequestMapping("/article")
public class ArticleController {
    @Autowired()
    private ArticleService articleService;

    @Autowired()
    private ModelMapper objectAssign;

    @GetMapping("/id/{id}")
    public ResponseEntity<GlobalResponseDto<GetArticleResponseDto>> getArticleById(@PathVariable("id") UUID id) {
        ArticlesEntity articlesEntity = articleService.getArticleById(id);

        GlobalResponseDto<GetArticleResponseDto> response = new GlobalResponseDto<>(
                true,
                "Data retrieved successfully.",
                objectAssign.map(articlesEntity, GetArticleResponseDto.class)
        );

        return ResponseEntity.ok(response);
    }

    @GetMapping("/name/{name}")
    public ResponseEntity<GlobalResponseDto<GetArticleResponseDto>> getArticleByName(@PathVariable("name") String name) {
        ArticlesEntity articlesEntity = articleService.getArticleByName(name);

        GlobalResponseDto<GetArticleResponseDto> response = new GlobalResponseDto<>(
                true,
                "Data retrieved successfully.",
                objectAssign.map(articlesEntity, GetArticleResponseDto.class)
        );

        return ResponseEntity.ok(response);
    }

    @PostMapping()
    public ResponseEntity<GlobalResponseDto<CreateArticleResponseDto>> createArticle(@Valid() @RequestBody() CreateArticleRequestDto createArticleRequestDto) {
        ArticlesEntity articlesEntity = articleService.createArticle(objectAssign.map(createArticleRequestDto, ArticlesEntity.class));

        GlobalResponseDto<CreateArticleResponseDto> response = new GlobalResponseDto<>(
                true,
                "Created successfully.",
                objectAssign.map(articlesEntity, CreateArticleResponseDto.class)
        );

        return ResponseEntity.ok(response);
    }

    @PatchMapping()
    public ResponseEntity<GlobalResponseDto<UpdateArticleResponseDto>> updateArticle(@Valid() @RequestBody() UpdateArticleRequestDto updateArticleRequestDto) {
        ArticlesEntity articlesEntity = articleService.updateArticle(objectAssign.map(updateArticleRequestDto, ArticlesEntity.class));

        GlobalResponseDto<UpdateArticleResponseDto> response = new GlobalResponseDto<>(
                true,
                "Updated successfully.",
                objectAssign.map(articlesEntity, UpdateArticleResponseDto.class)
        );

        return ResponseEntity.ok(response);
    }

    @DeleteMapping()
    public ResponseEntity<GlobalResponseDto<String>> deleteArticle(@Valid() @RequestBody() DeleteArticleRequestDto deleteArticleRequestDto) {
        articleService.deleteArticle(deleteArticleRequestDto.getArticleId());

        GlobalResponseDto<String> response = new GlobalResponseDto<>(
                true,
                "Deleted successfully."
        );

        return ResponseEntity.ok(response);
    }
}