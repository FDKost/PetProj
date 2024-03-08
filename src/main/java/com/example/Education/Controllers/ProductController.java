package com.example.Education.Controllers;

import com.example.Education.DAO.ProductDao;
import com.example.Education.Product;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.*;

@Repository
@AllArgsConstructor
public class ProductController {
    private final ProductDao dao;
    @PostMapping
    public Long createProduct(@RequestBody Product product){
        return dao.createProduct(product);
    }
    @GetMapping
    public Product readProduct(@RequestParam Long product_id){
        return dao.getProductById(product_id);
    }
    @PutMapping
    public void editProduct(@RequestBody Product product){
        dao.editProduct(product);
    }
    @DeleteMapping
    public void deleteProduct(@RequestParam long product_id){
        dao.deleteProduct(product_id);
    }
}
