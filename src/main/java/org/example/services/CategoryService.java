package org.example.services;

import org.example.entities.CategoriesEntity;
import org.example.exceptions.CategoryExceptions.CategoryExistedException;
import org.example.exceptions.CategoryExceptions.CategoryNotExistException;
import org.example.repositories.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service()
public class CategoryService {
    @Autowired()
    private CategoryRepository categoryRepository;

    public CategoriesEntity getCategoryById(UUID categoryId) {
        return categoryRepository.findById(categoryId)
                .orElseThrow(() -> new CategoryNotExistException("Category '" + categoryId + "' not exist"));
    }

    public CategoriesEntity getCategoryByName(String categoryName) {
        return categoryRepository.getCategoryByName(categoryName)
                .orElseThrow(() -> new CategoryNotExistException("Category '" + categoryName + "' not exist"));
    }

    public CategoriesEntity createCategory(CategoriesEntity categoriesEntity) {
        if(categoryRepository.existsByName(categoriesEntity.getCategoryName())) {
            throw new CategoryExistedException("Category '" + categoriesEntity.getCategoryName() + "' existed");
        }

        return categoryRepository.save(categoriesEntity);
    }

    public CategoriesEntity updateCategory(CategoriesEntity categoriesEntity) {
        if(categoryRepository.existsById(categoriesEntity.getCategoryId())) {
            return categoryRepository.save(categoriesEntity);
        }

        throw new CategoryNotExistException("Category '" + categoriesEntity.getCategoryId() + "' not exist");
    }

    public void deleteCategory(UUID categoryId) {
        if(categoryRepository.existsById(categoryId)) {
            categoryRepository.deleteById(categoryId);
            return;
        }

        throw new CategoryNotExistException("Category '" + categoryId + "' not exist");
    }
}
