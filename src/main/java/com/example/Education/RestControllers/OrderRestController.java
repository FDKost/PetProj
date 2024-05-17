package com.example.Education.RestControllers;

import com.example.Education.*;
import com.example.Education.DAO.*;
import com.example.Education.DTO.OrderRequestDTO;
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
        payment.setId_user(user.getId());
        Long paymentId = paymentDao.createPayment(payment);

        Order order = new Order();
        order.setId_user(user.getId());
        order.setId_payment(paymentId);
        Address address = addressDao.getAddressByUserId(user.getId());
        order.setId_address(address.getId_address());
        order.setDate(new java.util.Date());
        Long orderId = orderDao.createOrder(order);

        List<ProductsInOrder> cartItems = new ArrayList<>();
        for (int i = 0; i < orderRequestDTO.getProductIds().length; i++) {
            ProductsInOrder item = new ProductsInOrder();
            item.setProduct_id(orderRequestDTO.getProductIds()[i]);
            item.setQuantity(Long.valueOf(orderRequestDTO.getQuantities()[i]));
            cartItems.add(item);
        }
        Cart cart = cartDao.getCartByUserId(user.getId());
        productsInOrderDao.addProductsFromCart(orderId, cartItems);
        productCartDao.deleteAllFromProductCart(cart.getId_cart());

        return new ModelAndView("redirect:/home");

    }

    @GetMapping("/readOrder/{id_order}")
    public Order readOrder(@PathVariable Long id_order) {
        return orderDao.getOrderById(id_order);
    }

    @PutMapping("/editOrder")
    public void editOrder(@RequestBody Order order) {
        orderDao.editOrder(order);
    }

    @DeleteMapping("/deleteOrder/{id_order}")
    public void deleteOrder(@PathVariable Long id_order) {
        orderDao.deleteOrder(id_order);
    }
}
