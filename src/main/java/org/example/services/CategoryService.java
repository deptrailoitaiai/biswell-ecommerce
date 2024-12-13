package org.example.services;

import org.example.entities.CategoriesEntity;
import org.example.exceptions.RuntimeExceptions.ExistedException;
import org.example.exceptions.RuntimeExceptions.NotExistsException;
import org.example.repositories.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service()
public class CategoryService {
    @Autowired()
    private CategoryRepository categoryRepository;

    public CategoriesEntity getCategoryById(UUID categoryId) {
        return categoryRepository.findById(categoryId)
                .orElseThrow(() -> new NotExistsException("Category '" + categoryId + "' not exist"));
    }

    public CategoriesEntity getCategoryByName(String categoryName) {
        return categoryRepository.getCategoryByName(categoryName)
                .orElseThrow(() -> new NotExistsException("Category '" + categoryName + "' not exist"));
    }

    public CategoriesEntity createCategory(CategoriesEntity categoriesEntity) {
        if(categoryRepository.existsByName(categoriesEntity.getCategoryName())) {
            throw new ExistedException("Category '" + categoriesEntity.getCategoryName() + "' existed");
        }

        return categoryRepository.save(categoriesEntity);
    }

    public CategoriesEntity updateCategory(CategoriesEntity categoriesEntity) {
        if(categoryRepository.existsById(categoriesEntity.getCategoryId())) {
            return categoryRepository.save(categoriesEntity);
        }

        throw new NotExistsException("Category '" + categoriesEntity.getCategoryId() + "' not exist");
    }

    public void deleteCategory(UUID categoryId) {
        if(categoryRepository.existsById(categoryId)) {
            categoryRepository.deleteById(categoryId);
            return;
        }

        throw new NotExistsException("Category '" + categoryId + "' not exist");
    }
}
