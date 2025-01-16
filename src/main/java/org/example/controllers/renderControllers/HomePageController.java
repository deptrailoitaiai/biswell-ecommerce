package org.example.controllers.renderControllers;

import org.example.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller()
public class HomePageController {

    @Autowired
    private ProductRepository productRepository;

    @GetMapping("/")
    public String getHomePage(Model model) {
        
        model.addAttribute("dataList", productRepository.findTop8ByOrderById());
        return "index";
    }
}
