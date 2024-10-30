package com.example.education.mapper.cart;

import com.example.education.dto.cart.CartCreateEditDTO;
import com.example.education.entity.CartEntity;
import com.example.education.mapper.Mapper;
import org.springframework.stereotype.Component;

@Component
public class CartCreateEditMapper implements Mapper<CartCreateEditDTO, CartEntity> {
    @Override
    public CartEntity map(CartCreateEditDTO object) {
        CartEntity cart = new CartEntity();
        fillCartCreateEditDTO(object, cart);
        return cart;
    }

    @Override
    public CartEntity map(CartCreateEditDTO fromObject, CartEntity toObject) {
        fillCartCreateEditDTO(fromObject, toObject);
        return toObject;
    }

    private void fillCartCreateEditDTO(CartCreateEditDTO object, CartEntity cart) {
        cart.setUser(object.getUserid());
        cart.setCreatedIn(object.getCreatedin());
    }
}

