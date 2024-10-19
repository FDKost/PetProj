package com.example.education.controllers;

import com.example.education.dao.AddressDao;
import com.example.education.dao.UserDao;
import com.example.education.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/profile")
@RequiredArgsConstructor
public class ProfileController {
    private final UserDao dao;

    @GetMapping
    public String profile(@AuthenticationPrincipal UserDetails userDetails, Model model){
        String login = userDetails.getUsername();
        User user = dao.getUserByLogin(login);
        model.addAttribute("userId",user.getId());
        return "profile";
    }
}
