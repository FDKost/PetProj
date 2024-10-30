package com.example.education.controllers;

import com.example.education.dto.cart.CartCreateEditDTO;
import com.example.education.dto.cart.CartReadDTO;
import com.example.education.dto.product.ProductReadDTO;
import com.example.education.dto.user.UserReadDTO;
import com.example.education.entity.UserEntity;
import com.example.education.services.cart.CartServiceImpl;
import com.example.education.services.product.ProductServiceImpl;
import com.example.education.services.user.UserServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/order")
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class OrderController {
    private final ProductServiceImpl productService;
    private final CartServiceImpl cartService;
    private final UserServiceImpl userService;

    @GetMapping
    public String showOrderPage(Model model, @AuthenticationPrincipal UserDetails userDetails) {
        List<ProductReadDTO> products = productService.getAllProducts();
        Optional<UserReadDTO> user = userService.findByUsername(userDetails.getUsername());
        if (user.isPresent()) {
            Optional<CartReadDTO> existingCart=cartService.findCartByUserId(user.get().getId());
            if(existingCart.isPresent()){
                model.addAttribute("cartid", existingCart.get().getId());
            }else{
                CartReadDTO cart = cartService.create(new CartCreateEditDTO(new UserEntity(user.get().getId(), user.get().getLogin(), user.get().getPassword(), user.get().getRole()), LocalDate.now()));
                model.addAttribute("cartid", cart.getId());
            }
        }
        model.addAttribute("products", products);

        return "order";
    }
}
