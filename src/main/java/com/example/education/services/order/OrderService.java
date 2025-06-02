package com.example.education.services.order;

import com.example.education.dto.order.OrderCreateEditDTO;
import com.example.education.dto.order.OrderReadDTO;
import com.example.education.dto.payment.PaymentCreateEditDTO;
import com.example.education.dto.productcart.ProductCartCreateEditDTO;
import com.example.education.dto.productsinorder.ProductsInOrderCreateEditDTO;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.ui.Model;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface OrderService {
    Optional<OrderReadDTO> findById(UUID id);

    List<OrderReadDTO> getAllOrders();

    OrderReadDTO create(OrderCreateEditDTO orderDTO);

    Optional<OrderReadDTO> update(UUID id, OrderCreateEditDTO orderDTO);

    boolean delete(UUID id);

    void fillManageOrders(Model model);

    void fillShowOrderPage(Model model, UserDetails userDetails);

    void fillCreateOrder(OrderCreateEditDTO orderCreateEditDTO,
                         ProductCartCreateEditDTO productCartCreateEditDTO,
                         PaymentCreateEditDTO paymentCreateEditDTO,
                         ProductsInOrderCreateEditDTO productsInOrderCreateEditDTO);
}
