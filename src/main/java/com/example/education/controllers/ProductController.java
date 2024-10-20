package com.example.education.controllers;

import com.example.education.dto.product.ProductCreateEditDTO;
import com.example.education.dto.product.ProductReadDTO;
import com.example.education.services.ProductService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@AllArgsConstructor
@RequestMapping("/order")
public class ProductController {
    private final ProductService productService;
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
        productService.getAllProducts();
        return productService.getAllProducts();
    }

    @DeleteMapping("/deleteProduct")
    public void deleteProduct(@RequestParam UUID productId){
        productService.delete(productId);
    }
}
