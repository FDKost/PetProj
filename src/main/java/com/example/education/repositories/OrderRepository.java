package com.example.education.repositories;

import com.example.education.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface OrderRepository extends JpaRepository<Order, UUID> {

    Optional<Order> findOrderById(UUID id);
    List<Order> getAllOrders();
}
