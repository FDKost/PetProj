package com.example.education.controllers;

import com.example.education.services.order.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
public class AdminOrdersController {
    private final OrderService orderService;

    @GetMapping("/admin/orders")
    public String manageOrders(Model model) {
        orderService.fillManageOrders(model);

        return "admin/orders";
    }
}
