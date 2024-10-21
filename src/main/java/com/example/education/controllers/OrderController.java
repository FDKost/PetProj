package com.example.education.controllers;

import com.example.education.dto.cart.CartCreateEditDTO;
import com.example.education.dto.cart.CartReadDTO;
import com.example.education.dto.product.ProductReadDTO;
import com.example.education.dto.user.UserReadDTO;
import com.example.education.entity.User;
import com.example.education.services.CartService;
import com.example.education.services.ProductService;
import com.example.education.services.UserService;
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
    private final ProductService productService;
    private final CartService cartService;
    private final UserService userService;

    @GetMapping
    public String showOrderPage(Model model, @AuthenticationPrincipal UserDetails userDetails) {
        List<ProductReadDTO> products = productService.getAllProducts();
        Optional<UserReadDTO> user = userService.findByUsername(userDetails.getUsername());
        Optional<CartReadDTO> existingCart=cartService.findCartByUserId(user.get().getId());
        if(existingCart.isPresent()){
            model.addAttribute("cartid", existingCart.get().getId());
        }else{
            CartReadDTO cart = cartService.create(new CartCreateEditDTO(new User(user.get().getId(), user.get().getLogin(), user.get().getPassword(), user.get().getRole()), LocalDate.now()));
            model.addAttribute("cartid", cart.getId());
        }
        model.addAttribute("products", products);

        return "order";
    }
}
