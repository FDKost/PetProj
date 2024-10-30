package com.example.education.services.cart;

import com.example.education.dto.cart.CartCreateEditDTO;
import com.example.education.dto.cart.CartReadDTO;

import java.util.Optional;
import java.util.UUID;

public interface CartService {
    Optional<CartReadDTO> findById(UUID id);

    Optional<CartReadDTO> findCartByUserId(UUID userId);

    CartReadDTO create(CartCreateEditDTO cartDTO);

    Optional<CartReadDTO> update(UUID id,CartCreateEditDTO cartDTO);

    boolean delete(UUID id);
}
