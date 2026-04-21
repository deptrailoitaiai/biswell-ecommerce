package org.example.controllers.adminControllers;

import org.example.entities.ArticlesEntity;
import org.example.services.ArticleService;
import org.example.services.CloudinaryService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.UUID;

@Controller
@RequestMapping("/admin/news")
public class AdminNewsController {

    private final ArticleService articleService;
    private final CloudinaryService cloudinaryService;

    public AdminNewsController(ArticleService articleService, CloudinaryService cloudinaryService) {
        this.articleService = articleService;
        this.cloudinaryService = cloudinaryService;
    }

    @GetMapping
    public String list(@RequestParam(defaultValue = "0") int page,
                       @RequestParam(defaultValue = "10") int size,
                       Model model) {
        Page<ArticlesEntity> articlePage = articleService.getAll(
                PageRequest.of(page, size, Sort.by("createAt").descending()));
        model.addAttribute("articles", articlePage.getContent());
        model.addAttribute("totalPages", articlePage.getTotalPages());
        model.addAttribute("currentPage", page);
        return "admin/news/list";
    }

    @GetMapping("/add")
    public String addForm(Model model) {
        model.addAttribute("article", new ArticlesEntity());
        model.addAttribute("action", "add");
        return "admin/news/form";
    }

    @PostMapping("/save")
    public String save(@RequestParam String articleName,
                       @RequestParam(required = false) String summary,
                       @RequestParam(required = false) String text,
                       @RequestParam(required = false) MultipartFile thumbnail,
                       RedirectAttributes ra) {
        try {
            ArticlesEntity article = new ArticlesEntity();
            article.setArticleName(articleName);
            article.setSummary(summary);
            article.setText(text);
            if (thumbnail != null && !thumbnail.isEmpty()) {
                article.setThumbnail(cloudinaryService.uploadFile(thumbnail));
            }
            articleService.createArticle(article);
            ra.addFlashAttribute("success", "Thêm tin tức thành công!");
        } catch (Exception e) {
            ra.addFlashAttribute("error", e.getMessage());
            return "redirect:/admin/news/add";
        }
        return "redirect:/admin/news";
    }

    @GetMapping("/{id}/edit")
    public String editForm(@PathVariable UUID id, Model model) {
        model.addAttribute("article", articleService.getArticleById(id));
        model.addAttribute("action", "edit");
        return "admin/news/form";
    }

    @PostMapping("/{id}/update")
    public String update(@PathVariable UUID id,
                         @RequestParam String articleName,
                         @RequestParam(required = false) String summary,
                         @RequestParam(required = false) String text,
                         @RequestParam(required = false) MultipartFile thumbnail,
                         RedirectAttributes ra) {
        try {
            ArticlesEntity article = articleService.getArticleById(id);
            article.setArticleName(articleName);
            article.setSummary(summary);
            article.setText(text);
            if (thumbnail != null && !thumbnail.isEmpty()) {
                article.setThumbnail(cloudinaryService.uploadFile(thumbnail));
            }
            articleService.updateArticle(article);
            ra.addFlashAttribute("success", "Cập nhật tin tức thành công!");
        } catch (Exception e) {
            ra.addFlashAttribute("error", e.getMessage());
        }
        return "redirect:/admin/news";
    }

    @PostMapping("/{id}/delete")
    public String delete(@PathVariable UUID id, RedirectAttributes ra) {
        articleService.deleteArticle(id);
        ra.addFlashAttribute("success", "Đã xóa tin tức!");
        return "redirect:/admin/news";
    }
}
