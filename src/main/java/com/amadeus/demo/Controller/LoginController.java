package com.amadeus.demo.Controller;

//import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/auth")
public class LoginController {
    @GetMapping("/showUserString")
    public String showUserString() {
        return "test auth service";
    }
}