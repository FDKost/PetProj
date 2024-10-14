package com.example.education.controllers;

import com.example.education.entity.Cart;
import com.example.education.dao.CartDao;
import com.example.education.dao.ProductCartDao;
import com.example.education.dao.UserDao;
import com.example.education.entity.ProductCart;
import com.example.education.entity.User;
import lombok.AllArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/cart")
@AllArgsConstructor
public class CartController {
    private final ProductCartDao dao;
    private final UserDao userDao;
    private final CartDao cartDao;
    @GetMapping
    public String showCartPage(Model model,@AuthenticationPrincipal UserDetails userDetails) {
        String username = userDetails.getUsername();
        User user = userDao.getUserByLogin(username);
        Cart cart = cartDao.getCartByUserId(user.getId());
        List<ProductCart> cartItems = dao.getAllProductCartsByIdCart(cart.getCartId());
        double totalAmount = 0;
        for (ProductCart cartItem : cartItems) {
            totalAmount += cartItem.getProduct().getPrice() * cartItem.getQuantity();
        }
        model.addAttribute("cartItems", cartItems);
        model.addAttribute("totalAmount", totalAmount);
        return "cart";
    }
}
