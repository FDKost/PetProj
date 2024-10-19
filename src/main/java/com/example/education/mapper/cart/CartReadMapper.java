package com.example.education.mapper.cart;

import com.example.education.dto.cart.CartReadDTO;
import com.example.education.entity.Cart;
import com.example.education.mapper.Mapper;
import org.springframework.stereotype.Component;

@Component
public class CartReadMapper implements Mapper<Cart, CartReadDTO> {
    @Override
    public CartReadDTO map(Cart object) {
        return new CartReadDTO(
                object.getId(),
                object.getUser(),
                object.getCreatedIn());
    }
}
