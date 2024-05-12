package com.example.Education.RestControllers;

import com.example.Education.Cart;
import com.example.Education.DAO.CartDao;
import com.example.Education.DAO.UserDao;
import com.example.Education.User;
import lombok.AllArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.Date;

@RestController
@AllArgsConstructor
public class CartController {
    private final CartDao dao;
    private final UserDao userDao;

    @PostMapping("/api/cart_create")
    public ModelAndView createCart(Cart cart, @AuthenticationPrincipal UserDetails userDetails){
        String username = userDetails.getUsername();
        User user = userDao.getUserByLogin(username);
        cart.setId_user(user.getId());
        Date currentDate = new Date();
        cart.setCreated_in(currentDate);
        dao.createCart(cart);
        String orderUrl = ServletUriComponentsBuilder.fromCurrentContextPath().path("/order").toUriString();
        return new ModelAndView("redirect:" + orderUrl);
    }

    @GetMapping("/api/cart_read")
    public Cart readCart(@RequestParam Long id_cart){
        return dao.getCartById(id_cart);
    }

    @PutMapping("/api/cart_edit")
    public void editCart(@RequestBody Cart cart){
        dao.editCart(cart);
    }

    @DeleteMapping("/api/cart_delete")
    public void deleteCart(@RequestParam long id_cart){
        dao.deleteCart(id_cart);
    }
}
