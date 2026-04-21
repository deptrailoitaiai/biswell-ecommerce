package org.example.controllers.renderControllers;

import org.example.entities.ArticlesEntity;
import org.example.entities.V2Categories;
import org.example.services.ArticleService;
import org.example.services.V2CategoriesService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.UUID;

@Controller
@RequestMapping("/news")
public class NewsPageController {

    private final ArticleService articleService;
    private final V2CategoriesService v2CategoriesService;

    public NewsPageController(ArticleService articleService, V2CategoriesService v2CategoriesService) {
        this.articleService = articleService;
        this.v2CategoriesService = v2CategoriesService;
    }

    @ModelAttribute("categoriesMenuTop")
    public List<V2Categories> populateCategories() {
        return v2CategoriesService.getAll();
    }

    @GetMapping
    public String getNewsList(@RequestParam(defaultValue = "0") int page,
                              @RequestParam(defaultValue = "9") int size,
                              Model model) {
        Page<ArticlesEntity> newsPage = articleService.getAll(
                PageRequest.of(page, size, Sort.by("createAt").descending()));
        model.addAttribute("newsList", newsPage.getContent());
        model.addAttribute("totalPages", newsPage.getTotalPages());
        model.addAttribute("currentPage", page);
        model.addAttribute("baseUrl", "/news");
        return "news";
    }

    @GetMapping("/{id}")
    public String getNewsDetail(@PathVariable UUID id, Model model) {
        model.addAttribute("article", articleService.getArticleById(id));
        return "news-detail";
    }
}
