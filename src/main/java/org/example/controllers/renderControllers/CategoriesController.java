package org.example.controllers.renderControllers;

import org.example.entities.ProductEntity;
import org.example.entities.V2Categories;
import org.example.repositories.CategoryRepository;
import org.example.repositories.ProductRepository;
import org.example.repositories.V2CategoriesRepository;
import org.example.services.ProductService;
import org.example.services.V2CategoriesService;
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

    private final V2CategoriesService v2CategoriesService;
    private final ProductService productService;

    @Autowired
    public CategoriesController(V2CategoriesService v2CategoriesService, ProductService productService) {
        this.v2CategoriesService = v2CategoriesService;
        this.productService = productService;
    }

    @ModelAttribute("categoriesMenuTop")
    public List<V2Categories> populateCategories() {
        return v2CategoriesService.getAll();
    }

    @GetMapping("/{id}")
    public String getProductPage(@PathVariable Long id,
                                 @RequestParam(defaultValue = "0") int page,
                                 @RequestParam(defaultValue = "9") int size,
                                 Model model) {
        Pageable pageable = PageRequest.of(page, size);
        Page<ProductEntity> productPage = productService.getByCategoryId(id, pageable);
        V2Categories category = v2CategoriesService.getById(id);
        model.addAttribute("dataList", productPage);
        model.addAttribute("category", category);
        model.addAttribute("categoryId", id);
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", productPage.getTotalPages());
        return "san-pham";
    }

}
