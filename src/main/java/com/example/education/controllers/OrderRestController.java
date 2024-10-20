package com.example.education.controllers;

import com.example.education.dto.order.OrderCreateEditDTO;
import com.example.education.dto.order.OrderReadDTO;
import com.example.education.dto.payment.PaymentCreateEditDTO;
import com.example.education.dto.productcart.ProductCartCreateEditDTO;
import com.example.education.services.*;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@AllArgsConstructor
@RequestMapping("/orders")
public class OrderRestController {
    private final OrderService orderService;
    private final ProductCartService productCartService;
    private final PaymentService paymentService;

    @PostMapping("/createOrder")
    public ModelAndView createOrder(OrderCreateEditDTO orderCreateEditDTO,
                                    ProductCartCreateEditDTO productCartCreateEditDTO,
                                    PaymentCreateEditDTO paymentCreateEditDTO) {
        paymentService.create(paymentCreateEditDTO);
        orderService.create(orderCreateEditDTO);
        productCartService.deleteAllFromProductCartByCartId(productCartCreateEditDTO.getCartid().getId());
        return new ModelAndView("redirect:/home");
    }

    @GetMapping("/readOrder/{orderId}")
    public Optional<OrderReadDTO> readOrder(@PathVariable UUID orderId) {
        return  orderService.findById(orderId);
    }

    @PostMapping("/editOrder")
    public ModelAndView editOrder(@RequestParam UUID orderId,OrderCreateEditDTO orderCreateEditDTO) {
        Optional<OrderReadDTO> order = orderService.findById(orderId);
        if (order.isPresent()) {
            orderService.update(orderId,orderCreateEditDTO);
        }
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
