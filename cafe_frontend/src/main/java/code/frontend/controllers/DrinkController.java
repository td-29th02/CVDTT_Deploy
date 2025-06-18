package code.frontend.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class DrinkController {
    @GetMapping("/drink")
    public String userPage() {
        return "drinks/index";
    }
}
