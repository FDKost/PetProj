package com.example.education.controllers.restControllers;

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
        Long paymentId = paymentDao.createPayment(payment);

        Order order = new Order();
        order.setUserId(user.getId());
        order.setPaymentId(paymentId);
        Address address = addressDao.getAddressByUserId(user.getId());
        order.setAddressId(address.getAddressId());
        order.setDate(new java.util.Date());
        order.setStatus(1L);
        Long orderId = orderDao.createOrder(order);

        List<ProductsInOrder> cartItems = new ArrayList<>();
        for (int i = 0; i < orderRequestDTO.getProductIds().length; i++) {
            ProductsInOrder item = new ProductsInOrder();
            item.setProductId(orderRequestDTO.getProductIds()[i]);
            item.setQuantity(Long.valueOf(orderRequestDTO.getQuantities()[i]));
            cartItems.add(item);
        }
        Cart cart = cartDao.getCartByUserId(user.getId());
        productsInOrderDao.addProductsFromCart(orderId, cartItems);
        productCartDao.deleteAllFromProductCart(cart.getCartId());

        return new ModelAndView("redirect:/home");

    }

    @GetMapping("/readOrder/{orderId}")
    public Order readOrder(@PathVariable Long orderId) {
        return orderDao.getOrderById(orderId);
    }

    @PostMapping("/editOrder")
    public ModelAndView editOrder(@RequestParam Long orderId, @RequestParam Long status) {
        Order order = orderDao.getOrderById(orderId);
        if (order != null) {
            order.setStatus(status);
            orderDao.editOrder(order);
        }
        return new ModelAndView("redirect:/admin/orders");
    }

    @DeleteMapping("/deleteOrder/{orderId}")
    public void deleteOrder(@PathVariable Long orderId) {
        orderDao.deleteOrder(orderId);
    }
    @GetMapping("/getAllOrders")
    public List<Order> getAllOrders() {
        return orderDao.getAllOrders();
    }
}
