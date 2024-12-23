package org.example.controllers.renderControllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Controller()
public class HomePageController {
    @GetMapping("/index")
    public String getHomePage(Model model) {
        return "index.html";
    }
}
