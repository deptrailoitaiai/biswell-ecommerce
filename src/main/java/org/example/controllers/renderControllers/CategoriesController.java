package org.example.controllers.renderControllers;

import org.example.entities.ProductEntity;
import org.example.entities.V2Categories;
import org.example.repositories.CategoryRepository;
import org.example.repositories.ProductRepository;
import org.example.repositories.V2CategoriesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/categories")
public class CategoriesController {

    private final V2CategoriesRepository v2CategoriesRepository;
    private final ProductRepository productRepository;

    @Autowired
    public CategoriesController(V2CategoriesRepository v2CategoriesRepository, ProductRepository productRepository) {
        this.v2CategoriesRepository = v2CategoriesRepository;
        this.productRepository = productRepository;
    }

    @GetMapping("/{id}")
    public String getProductPage(@PathVariable Long id,
                                 @RequestParam(defaultValue = "0") int page,
                                 @RequestParam(defaultValue = "5") int size,
                                 Model model) {
        Pageable pageable = PageRequest.of(page, size);
        Page<ProductEntity> productPage = productRepository.findByCategory_CategoryId(id, pageable);
        V2Categories category = v2CategoriesRepository.findById(id).orElseThrow(() -> new RuntimeException("Product not found"));
        model.addAttribute("dataList", productPage);
        model.addAttribute("category", category);
        model.addAttribute("categoryId", id);
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", productPage.getTotalPages());
        return "san-pham";
    }

}
