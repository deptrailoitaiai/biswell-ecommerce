package org.example.services;

import org.example.entities.V2Categories;

import java.util.List;

public interface V2CategoriesService {
    List<V2Categories> getAll();
    V2Categories getById(long categoryId);
}
