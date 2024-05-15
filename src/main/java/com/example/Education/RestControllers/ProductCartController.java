package com.example.Education.RestControllers;

import com.example.Education.Cart;
import com.example.Education.DAO.CartDao;
import com.example.Education.DAO.ProductCartDao;
import com.example.Education.DAO.ProductDao;
import com.example.Education.DAO.UserDao;
import com.example.Education.ProductCart;
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
public class ProductCartController {
    private final ProductCartDao dao;
    private final ProductDao productDao;
    private final CartDao cartDao;
    private final UserDao userDao;

    @PostMapping("/api/create_productCart")
    public ModelAndView createProductCart(@RequestParam Long product_id,Cart cart ,ProductCart productCart, @AuthenticationPrincipal UserDetails userDetails){
        String username = userDetails.getUsername();
        User user = userDao.getUserByLogin(username);
        cart.setId_user(user.getId());
        Date date = new Date();
        cart.setCreated_in(date);
        cartDao.createCart(cart);
        cart = cartDao.getCartByUserId(user.getId());
        productCart.setId_cart(cart.getId_cart());
        productCart.setProduct_id(product_id);
        dao.createProductCart(productCart);
        String orderUrl = ServletUriComponentsBuilder.fromCurrentContextPath().path("/order").toUriString();
        return new ModelAndView("redirect:" + orderUrl);
    }
    @GetMapping("/api/getProductCart")
    public ProductCart readProductCart(@RequestParam long cart_item_id){
        return dao.getProductCartById(cart_item_id);
    }
    @PutMapping("/api/editProductCart")
    public void editProductCart(@RequestBody ProductCart productCart){
        dao.editProductCart(productCart);
    }
    @PostMapping("/api/deleteProductCart")
    public ModelAndView deleteProductCart( Long id_cart,long product_id){
        String cartUrl = ServletUriComponentsBuilder.fromCurrentContextPath().path("/cart").toUriString();
        dao.deleteProductCart(id_cart,product_id);
        return new ModelAndView("redirect:"+cartUrl);
    }
}
