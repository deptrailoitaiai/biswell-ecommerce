package org.example.controllers.adminControllers;

import org.example.entities.V2Categories;
import org.example.services.CloudinaryService;
import org.example.services.V2CategoriesService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/admin/categories")
public class AdminCategoryController {

    private final V2CategoriesService v2CategoriesService;
    private final CloudinaryService cloudinaryService;

    public AdminCategoryController(V2CategoriesService v2CategoriesService,
                                   CloudinaryService cloudinaryService) {
        this.v2CategoriesService = v2CategoriesService;
        this.cloudinaryService = cloudinaryService;
    }

    @GetMapping
    public String list(Model model) {
        model.addAttribute("categories", v2CategoriesService.getAll());
        return "admin/categories/list";
    }

    @GetMapping("/add")
    public String addForm(Model model) {
        model.addAttribute("category", new V2Categories());
        model.addAttribute("action", "add");
        return "admin/categories/form";
    }

    @PostMapping("/save")
    public String save(@RequestParam String categoryName,
                       @RequestParam(required = false) String description,
                       @RequestParam(required = false) MultipartFile categoryImage,
                       RedirectAttributes ra) {
        try {
            V2Categories category = new V2Categories();
            category.setCategoryName(categoryName);
            category.setDescription(description);
            if (categoryImage != null && !categoryImage.isEmpty()) {
                category.setCategoryImage(cloudinaryService.uploadFile(categoryImage));
            }
            v2CategoriesService.save(category);
            ra.addFlashAttribute("success", "Thêm danh mục thành công!");
        } catch (Exception e) {
            ra.addFlashAttribute("error", e.getMessage());
            return "redirect:/admin/categories/add";
        }
        return "redirect:/admin/categories";
    }

    @GetMapping("/{id}/edit")
    public String editForm(@PathVariable Long id, Model model) {
        model.addAttribute("category", v2CategoriesService.getById(id));
        model.addAttribute("action", "edit");
        return "admin/categories/form";
    }

    @PostMapping("/{id}/update")
    public String update(@PathVariable Long id,
                         @RequestParam String categoryName,
                         @RequestParam(required = false) String description,
                         @RequestParam(required = false) MultipartFile categoryImage,
                         RedirectAttributes ra) {
        try {
            V2Categories category = v2CategoriesService.getById(id);
            category.setCategoryName(categoryName);
            category.setDescription(description);
            if (categoryImage != null && !categoryImage.isEmpty()) {
                category.setCategoryImage(cloudinaryService.uploadFile(categoryImage));
            }
            v2CategoriesService.save(category);
            ra.addFlashAttribute("success", "Cập nhật danh mục thành công!");
        } catch (Exception e) {
            ra.addFlashAttribute("error", e.getMessage());
        }
        return "redirect:/admin/categories";
    }

    @PostMapping("/{id}/toggle-home")
    public String toggleHome(@PathVariable Long id, RedirectAttributes ra) {
        try {
            V2Categories category = v2CategoriesService.getById(id);
            category.setShowOnHome(!category.isShowOnHome());
            v2CategoriesService.save(category);
            ra.addFlashAttribute("success",
                    category.isShowOnHome() ? "Đã hiển thị \"" + category.getCategoryName() + "\" ở trang chủ!"
                                            : "Đã ẩn \"" + category.getCategoryName() + "\" khỏi trang chủ.");
        } catch (Exception e) {
            ra.addFlashAttribute("error", e.getMessage());
        }
        return "redirect:/admin/categories";
    }

    @PostMapping("/{id}/delete")
    public String delete(@PathVariable Long id, RedirectAttributes ra) {
        try {
            v2CategoriesService.delete(id);
            ra.addFlashAttribute("success", "Đã xóa danh mục!");
        } catch (Exception e) {
            ra.addFlashAttribute("error", "Không thể xóa danh mục đang có sản phẩm!");
        }
        return "redirect:/admin/categories";
    }
}
