package com.example.education.controllers.restControllers;

import com.example.education.entity.Cart;
import com.example.education.dao.CartDao;
import com.example.education.dao.ProductCartDao;
import com.example.education.dao.ProductDao;
import com.example.education.dao.UserDao;
import com.example.education.entity.ProductCart;
import com.example.education.entity.User;
import lombok.AllArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.Date;
import java.util.UUID;

@RestController
@AllArgsConstructor
public class ProductCartController {
    private final ProductCartDao productCartDao;
    private final ProductDao productDao;
    private final CartDao cartDao;
    private final UserDao userDao;

    @PostMapping("/api/createProductCart")
    public ModelAndView createProductCart(@RequestParam UUID productId, Cart cart , ProductCart productCart, @AuthenticationPrincipal UserDetails userDetails){
        String username = userDetails.getUsername();
        User user = userDao.getUserByLogin(username);

        cart.setUserId(user.getId());
        Date date = new Date();
        cart.setCreatedIn(date);
        cartDao.createCart(cart);

        cart = cartDao.getCartByUserId(user.getId());
        productCart.setCartId(cart.getId());
        productCart.setProductId(productId);
        productCartDao.createProductCart(productCart);

        String orderUrl = ServletUriComponentsBuilder.fromCurrentContextPath().path("/order").toUriString();
        return new ModelAndView("redirect:" + orderUrl);
    }
    @GetMapping("/api/getProductCart")
    public ProductCart readProductCart(@RequestParam UUID cartItemId){
        return productCartDao.getProductCartById(cartItemId);
    }
    @PutMapping("/api/editProductCart")
    public void editProductCart(ProductCart productCart,UUID cartId){
        productCartDao.editProductCart(productCart,cartId);
    }
    @PostMapping("/api/deleteProductCart")
    public ModelAndView deleteProductCart( UUID cartId,UUID productId){
        String cartUrl = ServletUriComponentsBuilder.fromCurrentContextPath().path("/cart").toUriString();
        productCartDao.deleteProductCart(cartId,productId);
        return new ModelAndView("redirect:"+cartUrl);
    }
}
