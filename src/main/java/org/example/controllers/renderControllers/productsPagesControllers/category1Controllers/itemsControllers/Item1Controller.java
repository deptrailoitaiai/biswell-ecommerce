package org.example.controllers.renderControllers.productsPagesControllers.category1Controllers.itemsControllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller()
public class Item1Controller {
    @GetMapping("/productOfCategory1")
    public String getItem1Page(Model model) {
        return "productOfCategory1";
    }
}
