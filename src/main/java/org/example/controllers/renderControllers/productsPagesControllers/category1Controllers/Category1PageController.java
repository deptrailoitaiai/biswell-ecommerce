package org.example.controllers.renderControllers.productsPagesControllers.category1Controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class Category1PageController {
    @GetMapping("/category1")
    public String getCategory1Page(Model model) {
        return "category1";
    }
}
