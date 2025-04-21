package com.example.education.controllers;

import com.example.education.services.cart.CartService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/cart")
@RequiredArgsConstructor
public class CartController {
    private final CartService cartService;

    @GetMapping
    public String showCartPage(Model model, @AuthenticationPrincipal UserDetails userDetails) {
        cartService.fillShowCartPage(model, userDetails);

        return "cart";
    }
}
