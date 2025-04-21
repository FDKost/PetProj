package com.example.education.controllers;

import com.example.education.dto.productsinorder.ProductsInOrderCreateEditDTO;
import com.example.education.dto.productsinorder.ProductsInOrderReadDTO;
import com.example.education.services.productsinorder.ProductsInOrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/productsInOrder")
@RequiredArgsConstructor
public class ProductsInOrderRestController {
    private final ProductsInOrderService productsInOrderService;

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
    public void editProductsInOrder(@RequestParam UUID productsInOrderId, @RequestBody ProductsInOrderCreateEditDTO productsInOrderCreateEditDTO) {
        productsInOrderService.update(productsInOrderId, productsInOrderCreateEditDTO);
    }

    @DeleteMapping("/delete/{orderItemId}")
    public void deleteProductsInOrder(@PathVariable UUID orderItemId) {
        productsInOrderService.delete(orderItemId);
    }
}
