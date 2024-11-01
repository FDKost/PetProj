package com.example.education.controllers;

import com.example.education.dto.product.ProductCreateEditDTO;
import com.example.education.dto.product.ProductReadDTO;
import com.example.education.services.product.ProductService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Lazy;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/order")
public class ProductController {
    @Qualifier("productServiceImpl")
    private final ProductService productService;

    public ProductController(@Lazy ProductService productService) {
        this.productService = productService;
    }

    @PostMapping("/createProduct")
    public ModelAndView createProduct(@RequestBody ProductCreateEditDTO productCreateEditDTO) {
        productService.create(productCreateEditDTO);

        return new ModelAndView("redirect:/admin/menu");
    }

    @GetMapping("/read")
    public Optional<ProductReadDTO> readProduct(@RequestParam UUID productId){
        return productService.findById(productId);
    }

    @PutMapping("/editProduct")
    public void editProduct(@RequestParam UUID productId,@RequestBody ProductCreateEditDTO productCreateEditDTO){
        productService.update(productId, productCreateEditDTO);
    }

    @GetMapping("/readAll")
    public List<ProductReadDTO> readAllProducts() {
        return productService.getAllProducts();
    }

    @DeleteMapping("/deleteProduct")
    public void deleteProduct(@RequestParam UUID productId){
        productService.delete(productId);
    }
}
