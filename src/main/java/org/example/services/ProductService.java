package org.example.services;

import org.example.dtos.requests.ProductDTO;
import org.example.entities.ProductEntity;

import java.util.List;

public interface ProductService {
    List<ProductEntity> getAllProducts();
    ProductEntity getProductById(Long id);
    List<ProductEntity> getTop8();
    ProductEntity addProduct(ProductDTO product);
}
