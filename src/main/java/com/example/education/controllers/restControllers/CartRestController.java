package com.example.education.controllers.restControllers;

import com.example.education.entity.Cart;
import com.example.education.dao.CartDao;
import com.example.education.dao.UserDao;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
public class CartRestController {
    private final CartDao cartDao;
    private final UserDao userDao;

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

    @GetMapping("/api/cart_read")
    public Cart readCart(@RequestParam Long cartId){
        return cartDao.getCartById(cartId);
    }

    @PutMapping("/api/cart_edit")
    public void editCart(@RequestBody Cart cart){
        cartDao.editCart(cart);
    }

    @DeleteMapping("/api/cart_delete")
    public void deleteCart(@RequestParam long cartId){
        cartDao.deleteCart(cartId);
    }
}
