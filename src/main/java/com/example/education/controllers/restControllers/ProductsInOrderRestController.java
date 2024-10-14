package com.example.education.controllers.restControllers;

import com.example.education.dao.ProductsInOrderDao;
import com.example.education.entity.ProductsInOrder;
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

    @GetMapping("/read/{orderItemId}")
    public ProductsInOrder readProductsInOrder(@PathVariable Long orderItemId) {
        return productsInOrderDao.getProductsInOrderById(orderItemId);
    }

    @GetMapping("/all")
    public List<ProductsInOrder> getAllProductsInOrder() {
        return productsInOrderDao.getAllProductsInOrder();
    }

    @PutMapping("/edit")
    public void editProductsInOrder(@RequestBody ProductsInOrder productsInOrder) {
        productsInOrderDao.editProductsInOrder(productsInOrder);
    }

    @DeleteMapping("/delete/{orderItemId}")
    public void deleteProductsInOrder(@PathVariable Long orderItemId) {
        productsInOrderDao.deleteProductsInOrder(orderItemId);
    }
}
