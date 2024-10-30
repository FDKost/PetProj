package com.example.education.mapper.order;

import com.example.education.dto.order.OrderReadDTO;
import com.example.education.entity.OrderEntity;
import com.example.education.mapper.Mapper;
import org.springframework.stereotype.Component;

@Component
public class OrderReadMapper implements Mapper<OrderEntity, OrderReadDTO> {
    @Override
    public OrderReadDTO map(OrderEntity object) {
        return new OrderReadDTO(
                object.getId(),
                object.getUser(),
                object.getPayment(),
                object.getAddress(),
                object.getDate(),
                object.getStatus());
    }
}
