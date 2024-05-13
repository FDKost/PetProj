package com.example.Education.Controllers;

import com.example.Education.Cart;
import com.example.Education.DAO.CartDao;
import com.example.Education.DAO.ProductCartDao;
import com.example.Education.DAO.UserDao;
import com.example.Education.Product;
import com.example.Education.ProductCart;
import com.example.Education.User;
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
        List<ProductCart> cartItems = dao.getAllProductCartsByIdCart(cart.getId_cart());
        double totalAmount = 0;
        for (ProductCart cartItem : cartItems) {
            totalAmount += cartItem.getProduct().getPrice() * cartItem.getQuantity();
        }
        model.addAttribute("cartItems", cartItems);
        model.addAttribute("totalAmount", totalAmount);
        return "cart";
    }
}
