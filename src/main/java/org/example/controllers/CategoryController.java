package org.example.controllers;

import java.util.Optional;
import java.util.UUID;

import jakarta.validation.Valid;
import org.example.dtos.requests.CategoryDtos.CreateCategoryRequestDto;
import org.example.dtos.requests.CategoryDtos.DeleteCategoryRequestDto;
import org.example.dtos.requests.CategoryDtos.UpdateCategoryRequestDto;
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
    public CategoriesEntity createCategory(@Valid() @RequestBody() CreateCategoryRequestDto createCategoryRequestDto) {
        CategoriesEntity categoriesEntity = entityNonNull.map(createCategoryRequestDto, CategoriesEntity.class);

        return categoryService.saveCategory(categoriesEntity);
    }

    @PatchMapping()
    public CategoriesEntity updateCategory(@Valid() @RequestBody() UpdateCategoryRequestDto updateCategoryRequestDto) {
        CategoriesEntity categoriesEntity = entityNonNull.map(updateCategoryRequestDto, CategoriesEntity.class);

        return categoryService.saveCategory(categoriesEntity);
    }

    @DeleteMapping()
    public void deleteCategory(@Valid() @RequestBody() DeleteCategoryRequestDto deleteCategoryRequestDto) {
        categoryService.deleteCategory(deleteCategoryRequestDto.getCategoryId());
    }
}
