/*
package com.example.Education.Controllers;

import com.example.Education.Cart;
import com.example.Education.DAO.CartDao;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
public class CartController {
    private final CartDao dao;

    @PostMapping
    public Long createCart(@RequestBody Cart cart){
        return dao.createCart(cart);
    }

    @GetMapping
    public Cart readCart(@RequestParam Long id_cart){
        return dao.getCartById(id_cart);
    }

    @PutMapping
    public void editCart(@RequestBody Cart cart){
        dao.editCart(cart);
    }

    @DeleteMapping
    public void deleteCart(@RequestParam long id_cart){
        dao.deleteCart(id_cart);
    }
}
*/
