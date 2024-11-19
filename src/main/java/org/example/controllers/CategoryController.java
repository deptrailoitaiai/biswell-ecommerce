package org.example.controllers;

import java.util.Optional;
import java.util.UUID;

import jakarta.validation.Valid;
import org.example.dtos.requests.CategoryDtos.CreateCategoryDto;
import org.example.dtos.requests.CategoryDtos.DeleteCategoryDto;
import org.example.dtos.requests.CategoryDtos.UpdateCategoryDto;
import org.example.entities.CategoriesEntity;
import org.example.services.CategoryService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController()
@RequestMapping("/category")
public class CategoryController {
    @Autowired()
    private CategoryService categoryService;

    @Autowired()
    private ModelMapper entityNonNull;

    @GetMapping("/id/{id}")
    public Optional<CategoriesEntity> getCategoryById(@PathVariable("id") UUID id) {
        return categoryService.getCategoryById(id);
    }

    @GetMapping("/name/{name}")
    public Optional<CategoriesEntity> getCategoryByName(@PathVariable("name") String name) {
        return categoryService.getCategoryByName(name);
    }

    @PostMapping()
    public CategoriesEntity createCategory(@Valid() @RequestBody() CreateCategoryDto createCategoryDto) {
        CategoriesEntity categoriesEntity = entityNonNull.map(createCategoryDto, CategoriesEntity.class);

        return categoryService.saveCategory(categoriesEntity);
    }

    @PatchMapping()
    public CategoriesEntity updateCategory(@Valid() @RequestBody() UpdateCategoryDto updateCategoryDto) {
        CategoriesEntity categoriesEntity = entityNonNull.map(updateCategoryDto, CategoriesEntity.class);

        return categoryService.saveCategory(categoriesEntity);
    }

    @DeleteMapping()
    public void deleteCategory(@Valid() @RequestBody() DeleteCategoryDto deleteCategoryDto) {
        categoryService.deleteCategory(deleteCategoryDto.getCategoryId());
    }
}
