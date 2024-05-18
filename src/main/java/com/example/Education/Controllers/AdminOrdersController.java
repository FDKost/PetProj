package com.example.Education.Controllers;

import com.example.Education.DAO.OrderDao;
import com.example.Education.Order;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
@AllArgsConstructor
public class AdminOrdersController {
    private final OrderDao orderDao;
    @GetMapping("/admin/orders")
    public String manageOrders(Model model) {
        List<Order> orders = orderDao.getAllOrders();
        model.addAttribute("orders", orders);
        return "admin/orders";
    }
}
