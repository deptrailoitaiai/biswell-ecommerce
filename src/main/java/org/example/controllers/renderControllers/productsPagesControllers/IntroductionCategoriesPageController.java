package org.example.controllers.renderControllers.productsPagesControllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller()
public class IntroductionCategoriesPageController {
    @GetMapping("/categories")
    public String getIntroductionCategoryPage(Model model) {
        return "categories";
    }
}
