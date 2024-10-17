package com.example.education.controllers.restControllers;

import com.example.education.dao.ProductsInOrderDao;
import com.example.education.entity.ProductsInOrder;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@AllArgsConstructor
@RequestMapping("/productsInOrder")
public class ProductsInOrderRestController {
    private final ProductsInOrderDao productsInOrderDao;

    @PostMapping("/create")
    public UUID createProductsInOrder(@RequestBody ProductsInOrder productsInOrder) {
        return productsInOrderDao.createProductsInOrder(productsInOrder);
    }

    @GetMapping("/read/{orderItemId}")
    public ProductsInOrder readProductsInOrder(@PathVariable UUID orderItemId) {
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
    public void deleteProductsInOrder(@PathVariable UUID orderItemId) {
        productsInOrderDao.deleteProductsInOrder(orderItemId);
    }
}
