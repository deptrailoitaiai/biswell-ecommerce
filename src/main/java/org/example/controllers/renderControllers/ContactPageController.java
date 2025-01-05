package org.example.controllers.renderControllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller()
public class ContactPageController {
    @GetMapping("/contact")
    public String getContactPage(Model model) {
        return "contact";
    }
}
