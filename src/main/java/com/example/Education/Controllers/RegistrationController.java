package com.example.Education.Controllers;


import com.example.Education.DAO.UserDao;
import com.example.Education.User;
import org.springframework.http.MediaType;
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
