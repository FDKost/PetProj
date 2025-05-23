package com.example.education.controllers;

import com.example.education.dto.cart.CartCreateEditDTO;
import com.example.education.dto.cart.CartReadDTO;
import com.example.education.services.cart.CartService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.ModelAndView;

import java.util.Optional;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
public class CartRestController {
    private final CartService cartService;

    @PostMapping("/api/cart_create")
    public ModelAndView createCart(CartCreateEditDTO cartCreateEditDTO) {
        cartService.create(cartCreateEditDTO);
        return new ModelAndView("redirect:/order");
    }

    @GetMapping("/api/cart_read")
    public Optional<CartReadDTO> readCart(@RequestParam UUID cartId) {
        return cartService.findById(cartId);
    }

    @PutMapping("/api/cart_edit")
    public CartReadDTO editCart(CartCreateEditDTO cartCreateEditDTO,
                                @RequestParam UUID cartId) {
        return cartService.update(cartId, cartCreateEditDTO)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    @DeleteMapping("/api/cart_delete")
    public void deleteCart(@RequestParam UUID cartId) {
        cartService.delete(cartId);
    }
}
