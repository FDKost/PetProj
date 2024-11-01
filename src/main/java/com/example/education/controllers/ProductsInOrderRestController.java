package com.example.education.controllers;

import com.example.education.dto.productsinorder.ProductsInOrderCreateEditDTO;
import com.example.education.dto.productsinorder.ProductsInOrderReadDTO;
import com.example.education.services.productsinorder.ProductsInOrderService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Lazy;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/productsInOrder")
public class ProductsInOrderRestController {
    @Qualifier("productsInOrderServiceImpl")
    private final ProductsInOrderService productsInOrderService;

    public ProductsInOrderRestController(@Lazy ProductsInOrderService productsInOrderService) {
        this.productsInOrderService = productsInOrderService;
    }

    @PostMapping("/create")
    public ProductsInOrderReadDTO createProductsInOrder(@RequestBody ProductsInOrderCreateEditDTO productsInOrderCreateEditDTO) {
        return productsInOrderService.create(productsInOrderCreateEditDTO);
    }

    @GetMapping("/read/{orderItemId}")
    public Optional<ProductsInOrderReadDTO> readProductsInOrder(@PathVariable UUID orderItemId) {
        return productsInOrderService.findById(orderItemId);
    }

    @GetMapping("/all")
    public List<ProductsInOrderReadDTO> getAllProductsInOrder() {
        return productsInOrderService.getAllProductsInOrder();
    }

    @PutMapping("/edit")
    public void editProductsInOrder(@RequestParam UUID productsInOrderId,@RequestBody ProductsInOrderCreateEditDTO productsInOrderCreateEditDTO) {
        productsInOrderService.update(productsInOrderId, productsInOrderCreateEditDTO);
    }

    @DeleteMapping("/delete/{orderItemId}")
    public void deleteProductsInOrder(@PathVariable UUID orderItemId) {
        productsInOrderService.delete(orderItemId);
    }
}
