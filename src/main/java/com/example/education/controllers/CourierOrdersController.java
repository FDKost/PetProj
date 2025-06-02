package com.example.education.controllers;

import com.example.education.services.order.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
public class CourierOrdersController {
    private final OrderService orderService;

    @GetMapping("/courier")
    public String manageOrders(Model model) {
        orderService.fillManageOrders(model);

        return "courier";
    }
}
