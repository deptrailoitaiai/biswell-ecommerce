package org.example.services.impl;

import org.example.entities.V2Categories;
import org.example.repositories.V2CategoriesRepository;
import org.example.services.V2CategoriesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class V2CategoriesServiceImpl implements V2CategoriesService {

    private final V2CategoriesRepository v2CategoriesRepository;

    @Autowired
    public V2CategoriesServiceImpl(V2CategoriesRepository v2CategoriesRepository) {
        this.v2CategoriesRepository = v2CategoriesRepository;
    }

    @Override
    public List<V2Categories> getAll() {
        return v2CategoriesRepository.findAll();
    }

    @Override
    public V2Categories getById(long categoryId) {
        return v2CategoriesRepository.findById(categoryId).orElseThrow(() -> new RuntimeException("Category not found"));
    }

    @Override
    public V2Categories save(V2Categories category) {
        return v2CategoriesRepository.save(category);
    }

    @Override
    public void delete(long categoryId) {
        v2CategoriesRepository.deleteById(categoryId);
    }

    @Override
    public long count() {
        return v2CategoriesRepository.count();
    }

    @Override
    public List<V2Categories> getHomepageCategories() {
        return v2CategoriesRepository.findByShowOnHomeTrueOrderByCategoryIdAsc();
    }
}
