package com.example.education.controllers;

import com.example.education.dto.order.OrderReadDTO;
import com.example.education.entity.StatusEntity;
import com.example.education.services.order.OrderServiceImpl;
import com.example.education.services.status.StatusServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;
import java.util.Optional;

@Controller
@AllArgsConstructor
public class AdminOrdersController {
    private final OrderServiceImpl orderService;
    private final StatusServiceImpl statusService;

    @GetMapping("/admin/orders")
    public String manageOrders(Model model) {
        List<OrderReadDTO> orders =orderService.getAllOrders();
        model.addAttribute("orders", orders);
        Optional<StatusEntity> statusComplete = statusService.findByDescription("Completed");

        statusComplete.ifPresent(statusEntity -> model.addAttribute("statusComplete", statusEntity.getId()));

        Optional<StatusEntity> statusInProcess = statusService.findByDescription("In process");
        statusInProcess.ifPresent(statusEntity -> model.addAttribute("statusPending", statusEntity.getId()));
        return "admin/orders";
    }
}
