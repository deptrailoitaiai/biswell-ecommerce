package org.example.controllers.renderControllers;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController()
@RequestMapping("/index")
public class HomePageController {
    @GetMapping()
    public String getHomePage(Model model) {
        return "index.html";
    }
}
