package org.example.repositories;

import java.util.List;

import org.example.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {

    List<Product> findTop8ByOrderById();
}