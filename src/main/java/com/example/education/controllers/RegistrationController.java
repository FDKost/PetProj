package com.example.education.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/registration")
public class RegistrationController {

    @GetMapping
    public String registerForm() {
        return "registration";
    }

}
