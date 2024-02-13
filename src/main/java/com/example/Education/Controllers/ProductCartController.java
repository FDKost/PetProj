package com.example.Education.Controllers;

import com.example.Education.DAO.ProductCartDao;
import com.example.Education.ProductCart;
import lombok.AllArgsConstructor;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.*;

@Repository
@AllArgsConstructor
public class ProductCartController {
    private final ProductCartDao dao;

    @PostMapping
    public Long createProductCart(@RequestBody ProductCart productCart){
        return dao.createProductCart(productCart);
    }
    @GetMapping
    public ProductCart readProductCart(@RequestParam long cart_item_id){
        return dao.getProductCartById(cart_item_id);
    }
    @PutMapping
    public void editProductCart(@RequestBody ProductCart productCart){
        dao.editProductCart(productCart);
    }
    @DeleteMapping
    public void deleteProductCart(@RequestParam long cart_item_id){
        dao.deleteProductCart(cart_item_id);
    }
}
