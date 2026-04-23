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
    public String privacyPolicy(org.springframework.ui.Model model) {
        model.addAttribute("canonicalUrl", "/chinh-sach-bao-mat");
        model.addAttribute("pageTitle", "Chính sách bảo mật - Biswell");
        model.addAttribute("metaDesc", "Chính sách bảo mật của Biswell — cam kết bảo vệ thông tin cá nhân khách hàng theo quy định pháp luật Việt Nam.");
        return "chinh-sach-bao-mat";
    }

    @GetMapping("/chinh-sach-mua-hang")
    public String purchasePolicy(org.springframework.ui.Model model) {
        model.addAttribute("canonicalUrl", "/chinh-sach-mua-hang");
        model.addAttribute("pageTitle", "Chính sách mua hàng & giao nhận - Biswell");
        model.addAttribute("metaDesc", "Hướng dẫn đặt hàng, thanh toán, giao nhận và đổi trả sản phẩm tại Biswell. Minh bạch, nhanh chóng, đảm bảo quyền lợi khách hàng.");
        return "chinh-sach-mua-hang";
    }
}
