package org.example.controllers.renderControllers;

import org.example.entities.ProductEntity;
import org.example.entities.V2Categories;
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

import java.util.List;

@Controller
@RequestMapping("/")
public class HomePageController {

    private final V2CategoriesService v2CategoriesService;
    private final ProductService productService;

    @Autowired
    public HomePageController(V2CategoriesService v2CategoriesService, ProductService productService) {
        this.v2CategoriesService = v2CategoriesService;
        this.productService = productService;
    }

    @GetMapping("/")
    public String getHomePage(Model model) {
        model.addAttribute("dataList", productService.getTop8());
        return "index";
    }

    @ModelAttribute("categoriesMenuTop")
    public List<V2Categories> populateCategories() {
        return v2CategoriesService.getAll();
    }

    @GetMapping("/categories")
    public String getCategoriesPage(Model model) {
        model.addAttribute("categoriesList", v2CategoriesService.getAll());
        return "categories";
    }

    @GetMapping("/introduction")
    public String getIntroductionPage(Model model) {
        return "introduction";
    }

    @GetMapping("/product")
    public String getProductPage(Model model) {
        int page = 0;
        int size = 9;
        long id = 1L;
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

    @GetMapping("/contact")
    public String getContactPage(Model model) {
        return "contact";
    }
}
