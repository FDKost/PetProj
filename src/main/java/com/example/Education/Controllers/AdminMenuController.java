package com.example.Education.Controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AdminMenuController {
    @GetMapping("/admin/menu")
    public String menu(){
        return "admin/menu";
    }
}
