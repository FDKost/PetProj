package com.example.education.services.order;

import com.example.education.dto.order.OrderCreateEditDTO;
import com.example.education.dto.order.OrderReadDTO;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface OrderService {
    Optional<OrderReadDTO> findById(UUID id);

    List<OrderReadDTO> getAllOrders();

    OrderReadDTO create(OrderCreateEditDTO orderDTO);

    Optional<OrderReadDTO> update(UUID id, OrderCreateEditDTO orderDTO);

    boolean delete(UUID id);
}
