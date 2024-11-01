package com.example.education.controllers;

import com.example.education.services.order.OrderService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/order")
public class OrderController {
    @Qualifier("orderServiceImpl")
    private final OrderService orderService;

    public OrderController(@Lazy OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping
    public String showOrderPage(Model model, @AuthenticationPrincipal UserDetails userDetails) {
        orderService.fillShowOrderPage(model, userDetails);

        return "order";
    }
}
