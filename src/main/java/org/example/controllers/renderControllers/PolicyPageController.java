package org.example.controllers.renderControllers;

import org.example.entities.V2Categories;
import org.example.services.V2CategoriesService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

import java.util.List;

@Controller
public class PolicyPageController {

    private final V2CategoriesService v2CategoriesService;

    public PolicyPageController(V2CategoriesService v2CategoriesService) {
        this.v2CategoriesService = v2CategoriesService;
    }

    @ModelAttribute("categoriesMenuTop")
    public List<V2Categories> populateCategories() {
        return v2CategoriesService.getAll();
    }

    @GetMapping("/chinh-sach-bao-mat")
    public String privacyPolicy() {
        return "chinh-sach-bao-mat";
    }

    @GetMapping("/chinh-sach-mua-hang")
    public String purchasePolicy() {
        return "chinh-sach-mua-hang";
    }
}
