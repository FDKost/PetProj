package com.example.education.controllers;

import com.example.education.dto.user.UserReadDTO;
import com.example.education.services.AddressService;
import com.example.education.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Optional;

@Controller
@RequestMapping("/profile")
@RequiredArgsConstructor
public class ProfileController {
    private final UserService userService;
    private final AddressService addressService;

    @GetMapping
    public String profile(@AuthenticationPrincipal UserDetails userDetails, Model model){
        String login = userDetails.getUsername();
        Optional<UserReadDTO> user = userService.findByUsername(login);
        model.addAttribute("userId",user.get().getId());
        return "profile";
    }
}
