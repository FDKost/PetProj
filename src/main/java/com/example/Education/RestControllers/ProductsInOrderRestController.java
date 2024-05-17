package com.example.Education.RestControllers;

import com.example.Education.DAO.ProductsInOrderDao;
import com.example.Education.ProductsInOrder;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/productsInOrder")
public class ProductsInOrderRestController {
    private final ProductsInOrderDao productsInOrderDao;

    @PostMapping("/create")
    public Long createProductsInOrder(@RequestBody ProductsInOrder productsInOrder) {
        return productsInOrderDao.createProductsInOrder(productsInOrder);
    }

    @GetMapping("/read/{order_item_id}")
    public ProductsInOrder readProductsInOrder(@PathVariable Long order_item_id) {
        return productsInOrderDao.getProductsInOrderById(order_item_id);
    }

    @GetMapping("/all")
    public List<ProductsInOrder> getAllProductsInOrder() {
        return productsInOrderDao.getAllProductsInOrder();
    }

    @PutMapping("/edit")
    public void editProductsInOrder(@RequestBody ProductsInOrder productsInOrder) {
        productsInOrderDao.editProductsInOrder(productsInOrder);
    }

    @DeleteMapping("/delete/{order_item_id}")
    public void deleteProductsInOrder(@PathVariable Long order_item_id) {
        productsInOrderDao.deleteProductsInOrder(order_item_id);
    }
}
