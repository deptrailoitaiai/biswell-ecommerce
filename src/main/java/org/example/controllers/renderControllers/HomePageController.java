package org.example.controllers.renderControllers;

import org.example.entities.ProductEntity;
import org.example.entities.V2Categories;
import org.example.services.ProductService;
import org.example.services.V2CategoriesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

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

    @GetMapping("/shop")
    public String getShopPage(@RequestParam(defaultValue = "0", required = false) Integer page,
                              @RequestParam(defaultValue = "9", required = false) Integer size,
                              Model model) {
        Page<ProductEntity> productPage = productService.getAllProducts(
                PageRequest.of(page, size, Sort.by("id").descending()));
        model.addAttribute("dataList", productPage.getContent());
        model.addAttribute("totalPages", productPage.getTotalPages());
        model.addAttribute("currentPage", page);
        return "shop";
    }

    @GetMapping("/contact")
    public String getContactPage(Model model) {
        return "contact";
    }
}
