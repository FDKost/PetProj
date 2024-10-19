package com.example.education.controllers;


import com.example.education.dao.UserDao;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/registration")
public class RegistrationController {
    private UserDao dao;

    @GetMapping
    public String registerForm() {
        return "registration";
    }

    /*@PostMapping
    public Long createUser(@RequestBody User user) {
        return dao.createUser(user);
    }*/
}
