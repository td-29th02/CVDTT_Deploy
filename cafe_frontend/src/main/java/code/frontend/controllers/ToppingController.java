package code.frontend.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ToppingController {
    @GetMapping("/topping")
    public String userPage() {
        return "toppings/index";
    }
}
