package org.example.controllers.renderControllers;

import org.example.entities.ProductEntity;
import org.example.repositories.CategoryRepository;
import org.example.repositories.ProductRepository;
import org.example.repositories.V2CategoriesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

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

    @GetMapping
    public String getCategoriesPage(Model model) {
        model.addAttribute("categoriesList", v2CategoriesRepository.findAll());
        return "categories";
    }

    @GetMapping("/{id}")
    public String getProductPage(@PathVariable Long id,
                                 @RequestParam(defaultValue = "0") int page,
                                 @RequestParam(defaultValue = "5") int size,
                                 Model model) {
        Pageable pageable = PageRequest.of(page, size);
        Page<ProductEntity> productPage = productRepository.findByCategory_CategoryId(id, pageable);
        model.addAttribute("dataList", productPage);
        model.addAttribute("categoryId", id);
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", productPage.getTotalPages());
        return "san-pham";
    }
}
