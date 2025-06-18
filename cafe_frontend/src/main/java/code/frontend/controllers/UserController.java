package code.frontend.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.CrossOrigin;

@Controller
//@CrossOrigin(origins = "http://localhost:8081", allowCredentials = "true")
public class UserController {
    @GetMapping("/user")
    public String userPage() {
        return "users/index";
    }
}
