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
        return v2CategoriesRepository.findById(categoryId).orElseThrow(() -> new RuntimeException("Product not found"));
    }
}
