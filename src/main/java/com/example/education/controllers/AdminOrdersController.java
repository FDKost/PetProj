package com.example.education.controllers;

import com.example.education.services.order.OrderServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@AllArgsConstructor
public class AdminOrdersController {
    private final OrderServiceImpl orderService;

    @GetMapping("/admin/orders")
    public String manageOrders(Model model) {
        orderService.fillManageOrders(model);

        return "admin/orders";
    }
}
