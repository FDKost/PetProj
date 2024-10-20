package com.example.education.mapper.order;

import com.example.education.dto.order.OrderCreateEditDTO;
import com.example.education.entity.Order;
import com.example.education.mapper.Mapper;
import org.springframework.stereotype.Component;

@Component
public class OrderCreateEditMapper implements Mapper<OrderCreateEditDTO, Order> {
    @Override
    public Order map(OrderCreateEditDTO object) {
        Order order = new Order();
        copy(object, order);
        return order;
    }

    @Override
    public Order map(OrderCreateEditDTO fromObject, Order toObject) {
        copy(fromObject, toObject);
        return toObject;
    }

    private void copy(OrderCreateEditDTO object, Order order) {
        order.setUser(object.getUserid());
        order.setPayment(object.getPaymentid());
        order.setAddress(object.getAddressid());
        order.setDate(object.getDate());
        order.setStatus(object.getStatus());
    }
}
