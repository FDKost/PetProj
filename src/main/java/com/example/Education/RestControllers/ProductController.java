package com.example.Education.RestControllers;

import com.example.Education.DAO.ProductDao;
import com.example.Education.Product;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/order")
public class ProductController {
    private final ProductDao dao;
    @PostMapping("/createProduct")
    public ModelAndView createProduct(@ModelAttribute Product product){
        dao.createProduct(product);
        return new ModelAndView("redirect:/admin/menu");
    }
    @GetMapping("/read")
    public Product readProduct(@RequestParam Long product_id){
        return dao.getProductById(product_id);
    }

    @PutMapping("/editProduct")
    public void editProduct(@RequestBody Product product){
        dao.editProduct(product);
    }
    @GetMapping("/readAll")
    public List<Product> readAllProducts() {
        return dao.getAllProducts();
    }
    @DeleteMapping("/deleteProduct")
    public void deleteProduct(@RequestParam long product_id){
        dao.deleteProduct(product_id);
    }
}
