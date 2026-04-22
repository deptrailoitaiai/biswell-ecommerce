package org.example.controllers.adminControllers;

import org.example.services.ArticleService;
import org.example.services.ProductService;
import org.example.services.V2CategoriesService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin")
public class AdminDashboardController {

    private final ProductService productService;
    private final V2CategoriesService v2CategoriesService;
    private final ArticleService articleService;

    public AdminDashboardController(ProductService productService,
                                    V2CategoriesService v2CategoriesService,
                                    ArticleService articleService) {
        this.productService = productService;
        this.v2CategoriesService = v2CategoriesService;
        this.articleService = articleService;
    }

    @GetMapping({"", "/"})
    public String dashboard(Model model) {
        model.addAttribute("totalProducts", productService.count());
        model.addAttribute("totalCategories", v2CategoriesService.count());
        model.addAttribute("totalArticles", articleService.count());
        model.addAttribute("latestProducts", productService.getTop8());
        return "admin/dashboard";
    }
}
