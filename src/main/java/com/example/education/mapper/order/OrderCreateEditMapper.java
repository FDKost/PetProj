package com.example.education.mapper.order;

import com.example.education.dto.order.OrderCreateEditDTO;
import com.example.education.entity.OrderEntity;
import com.example.education.mapper.Mapper;
import org.springframework.stereotype.Component;

@Component
public class OrderCreateEditMapper implements Mapper<OrderCreateEditDTO, OrderEntity> {
    @Override
    public OrderEntity map(OrderCreateEditDTO object) {
        OrderEntity order = new OrderEntity();
        fillOrderCreateEditDTO(object, order);
        return order;
    }

    @Override
    public OrderEntity map(OrderCreateEditDTO fromObject, OrderEntity toObject) {
        fillOrderCreateEditDTO(fromObject, toObject);
        return toObject;
    }

    private void fillOrderCreateEditDTO(OrderCreateEditDTO object, OrderEntity order) {
        order.setUser(object.getUserid());
        order.setPayment(object.getPaymentid());
        order.setAddress(object.getAddressid());
        order.setDate(object.getDate());
        order.setStatus(object.getStatus());
    }
}
