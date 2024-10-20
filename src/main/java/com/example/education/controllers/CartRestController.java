package com.example.education.controllers;

import com.example.education.dto.cart.CartCreateEditDTO;
import com.example.education.dto.cart.CartReadDTO;
import com.example.education.services.CartService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.Optional;
import java.util.UUID;

@RestController
@AllArgsConstructor
public class CartRestController {
    private final CartService cartService;

    /*@PostMapping("/api/cart_create")
    public ModelAndView createCart(Cart cart, @AuthenticationPrincipal UserDetails userDetails){
        String username = userDetails.getUsername();
        User user = userDao.getUserByLogin(username);
        cart.setId_user(user.getId());
        Date currentDate = new Date();
        cart.setCreated_in(currentDate);
        dao.createCart(cart);
        String orderUrl = ServletUriComponentsBuilder.fromCurrentContextPath().path("/order").toUriString();
        return new ModelAndView("redirect:" + orderUrl);
    }*/
    @PostMapping("/api/cart_create")
    public ModelAndView createCart(CartCreateEditDTO cartCreateEditDTO) {
        cartService.create(cartCreateEditDTO);

        String orderUrl = ServletUriComponentsBuilder.fromCurrentContextPath().path("/order").toUriString();
        return new ModelAndView("redirect:" + orderUrl);
    }

    @GetMapping("/api/cart_read")
    public Optional<CartReadDTO> readCart(@RequestParam UUID cartId){
        return cartService.findById(cartId);
    }

    @PutMapping("/api/cart_edit")
    public CartReadDTO editCart(@RequestBody CartCreateEditDTO cartCreateEditDTO,
                         @RequestParam UUID cartId){
        return cartService.update(cartId, cartCreateEditDTO)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    @DeleteMapping("/api/cart_delete")
    public void deleteCart(@RequestParam UUID cartId){
        cartService.delete(cartId);
    }
}
