package com.example.education.controllers;

import com.example.education.services.order.OrderServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/order")
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class OrderController {
    private final OrderServiceImpl orderService;

    @GetMapping
    public String showOrderPage(Model model, @AuthenticationPrincipal UserDetails userDetails) {
        orderService.fillShowOrderPage(model, userDetails);

        return "order";
    }
}
