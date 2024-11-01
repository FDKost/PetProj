package com.example.education.controllers;

import com.example.education.services.order.OrderService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AdminOrdersController {
    @Qualifier("orderServiceImpl")
    private final OrderService orderService;

    public AdminOrdersController(@Lazy OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping("/admin/orders")
    public String manageOrders(Model model) {
        orderService.fillManageOrders(model);

        return "admin/orders";
    }
}
