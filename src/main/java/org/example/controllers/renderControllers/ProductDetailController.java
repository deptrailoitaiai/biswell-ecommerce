package org.example.controllers.renderControllers;

import org.example.entities.ProductEntity;
import org.example.entities.V2Categories;
import org.example.services.ProductService;
import org.example.services.V2CategoriesService;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/san-pham")
public class ProductDetailController {

    private final ProductService productService;
    private final V2CategoriesService v2CategoriesService;

    public ProductDetailController(ProductService productService, V2CategoriesService v2CategoriesService) {
        this.productService = productService;
        this.v2CategoriesService = v2CategoriesService;
    }

    @ModelAttribute("categoriesMenuTop")
    public List<V2Categories> populateCategories() {
        return v2CategoriesService.getAll();
    }

    @GetMapping("/{slug}")
    public String detail(@PathVariable String slug, Model model) {
        ProductEntity product = productService.getBySlug(slug);

        List<String> images = new ArrayList<>();
        if (product.getMainImagePath() != null) images.add(product.getMainImagePath());
        if (product.getPimages() != null) images.addAll(product.getPimages());

        List<ProductEntity> related = product.getCategory() != null
                ? productService.getByCategoryId(product.getCategory().getCategoryId(), PageRequest.of(0, 6)).getContent()
                : List.of();

        model.addAttribute("product", product);
        model.addAttribute("images", images);
        model.addAttribute("referProduct", related);

        // SEO
        model.addAttribute("pageTitle", product.getMetaTitle() != null ? product.getMetaTitle() : product.getPname() + " - Biswell");
        model.addAttribute("metaDesc", product.getMetaDescription() != null ? product.getMetaDescription() : "");
        model.addAttribute("canonicalUrl", "/san-pham/" + slug);

        return "detail";
    }
}
