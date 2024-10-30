package com.example.education.mapper.cart;

import com.example.education.dto.cart.CartReadDTO;
import com.example.education.entity.CartEntity;
import com.example.education.mapper.Mapper;
import org.springframework.stereotype.Component;

@Component
public class CartReadMapper implements Mapper<CartEntity, CartReadDTO> {
    @Override
    public CartReadDTO map(CartEntity object) {
        return new CartReadDTO(
                object.getId(),
                object.getUser(),
                object.getCreatedIn());
    }
}
