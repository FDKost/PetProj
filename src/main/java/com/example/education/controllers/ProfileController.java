package com.example.education.controllers;

import com.example.education.services.userbank.UserBankService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/profile")
public class ProfileController {
    @Qualifier("userBankServiceImpl")
    private final UserBankService userBankService;

    public ProfileController(@Lazy UserBankService userBankService) {
        this.userBankService = userBankService;
    }

    @GetMapping
    public String profile(@AuthenticationPrincipal UserDetails userDetails, Model model){
        userBankService.fillProfile(userDetails, model);

        return "profile";
    }
}
