package com.example.education.controllers;

import com.example.education.dto.order.OrderCreateEditDTO;
import com.example.education.dto.order.OrderReadDTO;
import com.example.education.dto.payment.PaymentCreateEditDTO;
import com.example.education.dto.productcart.ProductCartCreateEditDTO;
import com.example.education.services.order.OrderService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Lazy;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/orders")
public class OrderRestController {
    @Qualifier("orderServiceImpl")
    private final OrderService orderService;

    public OrderRestController(@Lazy OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping("/createOrder")
    public ModelAndView createOrder(OrderCreateEditDTO orderCreateEditDTO,
                                    ProductCartCreateEditDTO productCartCreateEditDTO,
                                    PaymentCreateEditDTO paymentCreateEditDTO) {
        orderService.fillCreateOrder(orderCreateEditDTO, productCartCreateEditDTO, paymentCreateEditDTO);
        return new ModelAndView("redirect:/home");
    }

    @GetMapping("/readOrder/{orderId}")
    public Optional<OrderReadDTO> readOrder(@PathVariable UUID orderId) {
        return  orderService.findById(orderId);
    }

    @PostMapping("/editOrder")
    public ModelAndView editOrder(@RequestParam UUID orderId,
                                  OrderCreateEditDTO orderCreateEditDTO) {
        orderService.update(orderId, orderCreateEditDTO);
        return new ModelAndView("redirect:/admin/orders");
    }

    @DeleteMapping("/deleteOrder/{orderId}")
    public void deleteOrder(@PathVariable UUID orderId) {
        orderService.delete(orderId);
    }

    @GetMapping("/getAllOrders")
    public List<OrderReadDTO> getAllOrders() {
        return orderService.getAllOrders();
    }
}
