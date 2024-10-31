package com.example.education.controllers;

import com.example.education.services.userbank.UserBankServiceImpl;
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
    private final UserBankServiceImpl userBankService;

    @GetMapping
    public String profile(@AuthenticationPrincipal UserDetails userDetails, Model model){
        userBankService.fillProfile(userDetails, model);

        return "profile";
    }
}
