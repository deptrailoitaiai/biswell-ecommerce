package org.example.services;

import org.example.entities.CategoriesEntity;
import org.example.entities.ItemsEntity;
import org.example.repositories.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Optional;
import java.util.UUID;

@Service()
public class CategoryService {
    @Autowired()
    private CategoryRepository categoryRepository;

    public Optional<CategoriesEntity> getCategoryById(UUID categoryId) {
        return categoryRepository.findById(categoryId);
    }

    public CategoriesEntity saveCategory(CategoriesEntity categoriesEntity) {
        return categoryRepository.save(categoriesEntity);
    }

    public void deleteCategory(UUID categoryId) {
        categoryRepository.deleteById(categoryId);
    }

    public Optional<CategoriesEntity> getCategoryByName(String categoryName) {
        return categoryRepository.getCategoryByName(categoryName);
    }
}
