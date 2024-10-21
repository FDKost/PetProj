package com.example.education.controllers;

import com.example.education.dto.order.OrderReadDTO;
import com.example.education.services.OrderService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
@AllArgsConstructor
public class AdminOrdersController {
    private final OrderService orderService;

    @GetMapping("/admin/orders")
    public String manageOrders(Model model) {
        /*List<Order> orders = orderDao.getAllOrders();*/
        List<OrderReadDTO> orders =orderService.getAllOrders();
        model.addAttribute("orders", orders);
        return "admin/orders";
    }
}
