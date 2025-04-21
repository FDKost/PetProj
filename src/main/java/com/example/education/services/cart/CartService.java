package com.example.education.services.cart;

import com.example.education.dto.cart.CartCreateEditDTO;
import com.example.education.dto.cart.CartReadDTO;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.ui.Model;

import java.util.Optional;
import java.util.UUID;

public interface CartService {
    Optional<CartReadDTO> findById(UUID id);

    Optional<CartReadDTO> findCartByUserId(UUID userId);

    CartReadDTO create(CartCreateEditDTO cartDTO);

    Optional<CartReadDTO> update(UUID id, CartCreateEditDTO cartDTO);

    boolean delete(UUID id);

    void fillShowCartPage(Model model, UserDetails userDetails);
}
