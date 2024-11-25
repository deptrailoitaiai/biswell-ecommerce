package org.example.controllers;

import java.util.UUID;
import jakarta.validation.Valid;
import org.example.dtos.requests.CategoryDtos.CreateCategoryRequestDto;
import org.example.dtos.requests.CategoryDtos.DeleteCategoryRequestDto;
import org.example.dtos.requests.CategoryDtos.UpdateCategoryRequestDto;
import org.example.dtos.responses.CategoryDtos.CreateCategoryResponseDto;
import org.example.dtos.responses.CategoryDtos.GetCategoryResponseDto;
import org.example.dtos.responses.CategoryDtos.UpdateCategoryResponseDto;
import org.example.dtos.responses.GlobalResponseDto;
import org.example.entities.CategoriesEntity;
import org.example.services.CategoryService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController()
@RequestMapping("/category")
public class CategoryController {
    @Autowired()
    private CategoryService categoryService;

    @Autowired()
    private ModelMapper objectAssign;

    @GetMapping("/id/{id}")
    public ResponseEntity<GlobalResponseDto<GetCategoryResponseDto>> getCategoryById(@PathVariable("id") UUID id) {
        CategoriesEntity categoriesEntity = categoryService.getCategoryById(id);

        GlobalResponseDto<GetCategoryResponseDto> response = new GlobalResponseDto<>(
                true,
                "Data retrieved successfully.",
                objectAssign.map(categoriesEntity, GetCategoryResponseDto.class)
        );

        return ResponseEntity.ok(response);
    }

    @GetMapping("/name/{name}")
    public ResponseEntity<GlobalResponseDto<GetCategoryResponseDto>> getCategoryByName(@PathVariable("name") String name) {
        CategoriesEntity categoriesEntity = categoryService.getCategoryByName(name);

        GlobalResponseDto<GetCategoryResponseDto> response = new GlobalResponseDto<>(
                true,
                "Data retrieved successfully.",
                objectAssign.map(categoriesEntity, GetCategoryResponseDto.class)
        );

        return ResponseEntity.ok(response);
    }

    @PostMapping()
    public ResponseEntity<GlobalResponseDto<CreateCategoryResponseDto>> createCategory(@Valid() @RequestBody() CreateCategoryRequestDto createCategoryRequestDto) {
        CategoriesEntity categoriesEntity = categoryService.createCategory(objectAssign.map(createCategoryRequestDto, CategoriesEntity.class));

        GlobalResponseDto<CreateCategoryResponseDto> response = new GlobalResponseDto<>(
                true,
                "Created successfully.",
                objectAssign.map(categoriesEntity, CreateCategoryResponseDto.class)
        );

        return ResponseEntity.ok(response);
    }

    @PatchMapping()
    public ResponseEntity<GlobalResponseDto<UpdateCategoryResponseDto>> updateCategory(@Valid() @RequestBody() UpdateCategoryRequestDto updateCategoryRequestDto) {
        CategoriesEntity categoriesEntity = categoryService.updateCategory(objectAssign.map(updateCategoryRequestDto, CategoriesEntity.class));

        GlobalResponseDto<UpdateCategoryResponseDto> response = new GlobalResponseDto<>(
                true,
                "Updated successfully.",
                objectAssign.map(categoriesEntity, UpdateCategoryResponseDto.class)
        );

        return ResponseEntity.ok(response);
    }

    @DeleteMapping()
    public ResponseEntity<GlobalResponseDto<String>> deleteCategory(@Valid() @RequestBody() DeleteCategoryRequestDto deleteCategoryRequestDto) {
        categoryService.deleteCategory(deleteCategoryRequestDto.getCategoryId());

        GlobalResponseDto<String> response = new GlobalResponseDto<>(
                true,
                "Deleted successfully."
        );

        return ResponseEntity.ok(response);
    }
}
