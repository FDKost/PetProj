package com.example.education.controllers;

import com.example.education.services.cart.CartServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/cart")
@AllArgsConstructor
public class CartController {
    private final CartServiceImpl cartService;

    @GetMapping
    public String showCartPage(Model model,@AuthenticationPrincipal UserDetails userDetails) {
        cartService.fillShowCartPage(model, userDetails);

        return "cart";
    }
}
