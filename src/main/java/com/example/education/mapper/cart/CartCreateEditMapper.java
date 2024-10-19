package com.example.education.mapper.cart;

import com.example.education.dto.cart.CartCreateEditDTO;
import com.example.education.entity.Cart;
import com.example.education.mapper.Mapper;
import org.springframework.stereotype.Component;

@Component
public class CartCreateEditMapper implements Mapper<CartCreateEditDTO, Cart> {
    @Override
    public Cart map(CartCreateEditDTO object) {
        Cart cart = new Cart();
        copy(object, cart);
        return cart;
    }

    @Override
    public Cart map(CartCreateEditDTO fromObject, Cart toObject) {
        copy(fromObject, toObject);
        return toObject;
    }

    private void copy(CartCreateEditDTO object, Cart cart) {
        cart.setUser(object.getUserid());
        cart.setCreatedIn(object.getCreatedin());
    }
}
