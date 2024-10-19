package com.example.education.controllers;

import com.example.education.dao.*;
import com.example.education.dto.OrderRequestDTO;
import com.example.education.entity.*;
import lombok.AllArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@RestController
@AllArgsConstructor
@RequestMapping("/orders")
public class OrderRestController {
    private final OrderDao orderDao;
    private final PaymentDao paymentDao;
    private final ProductsInOrderDao productsInOrderDao;
    private final UserDao userDao;
    private final AddressDao addressDao;
    private final ProductCartDao productCartDao;
    private final CartDao cartDao;

    @PostMapping("/createOrder")
    public ModelAndView createOrder(@ModelAttribute OrderRequestDTO orderRequestDTO, @AuthenticationPrincipal UserDetails userDetails) {

        String login = userDetails.getUsername();
        User user = userDao.getUserByLogin(login);

        Payment payment = new Payment();
        payment.setTotal(orderRequestDTO.getTotalAmount().longValue());  // Преобразуем Double в Long
        payment.setUserId(user.getId());
        UUID paymentId = paymentDao.createPayment(payment);

        Order order = new Order();
        order.setUserId(user.getId());
        order.setPaymentId(paymentId);
        Address address = addressDao.getAddressByUserId(user.getId());
        order.setAddressId(address.getId());
        order.setDate(new java.util.Date());
        UUID orderId = orderDao.createOrder(order);

        List<ProductsInOrder> cartItems = new ArrayList<>();
        for (int i = 0; i < orderRequestDTO.getProductIds().length; i++) {
            ProductsInOrder item = new ProductsInOrder();
            item.setProductId(orderRequestDTO.getProductIds()[i]);
            item.setQuantity(Long.valueOf(orderRequestDTO.getQuantities()[i]));
            cartItems.add(item);
        }
        Cart cart = cartDao.getCartByUserId(user.getId());
        productsInOrderDao.addProductsFromCart(orderId, cartItems);
        productCartDao.deleteAllFromProductCart(cart.getId());

        return new ModelAndView("redirect:/home");
    }

    @GetMapping("/readOrder/{orderId}")
    public Order readOrder(@PathVariable UUID orderId) {
        return orderDao.getOrderById(orderId);
    }

    @PostMapping("/editOrder")
    public ModelAndView editOrder(@RequestParam UUID orderId, @RequestParam UUID status) {
        Order order = orderDao.getOrderById(orderId);
        if (order != null) {
            order.setStatus(status);
            orderDao.editOrder(order);
        }
        return new ModelAndView("redirect:/admin/orders");
    }

    @DeleteMapping("/deleteOrder/{orderId}")
    public void deleteOrder(@PathVariable UUID orderId) {
        orderDao.deleteOrder(orderId);
    }

    @GetMapping("/getAllOrders")
    public List<Order> getAllOrders() {
        return orderDao.getAllOrders();
    }
}
