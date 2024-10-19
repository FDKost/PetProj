package com.example.education.controllers;

import com.example.education.dao.ProductDao;
import com.example.education.entity.Product;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.UUID;

@RestController
@AllArgsConstructor
@RequestMapping("/order")
public class ProductController {
    private final ProductDao productDao;
    @PostMapping("/createProduct")
    public ModelAndView createProduct(@ModelAttribute Product product){
        productDao.createProduct(product);
        return new ModelAndView("redirect:/admin/menu");
    }

    @GetMapping("/read")
    public Product readProduct(@RequestParam UUID productId){
        return productDao.getProductById(productId);
    }

    @PutMapping("/editProduct")
    public void editProduct(@RequestBody Product product){
        productDao.editProduct(product);
    }

    @GetMapping("/readAll")
    public List<Product> readAllProducts() {
        return productDao.getAllProducts();
    }

    @DeleteMapping("/deleteProduct")
    public void deleteProduct(@RequestParam UUID productId){
        productDao.deleteProduct(productId);
    }
}
