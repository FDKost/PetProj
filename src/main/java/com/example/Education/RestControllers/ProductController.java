package com.example.Education.RestControllers;

import com.example.Education.DAO.ProductDao;
import com.example.Education.Product;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/order")
public class ProductController {
    private final ProductDao dao;
    @PostMapping
    public Long createProduct(@RequestBody Product product){
        return dao.createProduct(product);
    }
    @GetMapping("/order/read")
    public Product readProduct(@RequestParam Long product_id){
        return dao.getProductById(product_id);
    }

    @PutMapping
    public void editProduct(@RequestBody Product product){
        dao.editProduct(product);
    }
    @GetMapping("/order/readAll")
    public List<Product> readAllProducts() {
        return dao.getAllProducts();
    }
    @DeleteMapping
    public void deleteProduct(@RequestParam long product_id){
        dao.deleteProduct(product_id);
    }
}
