package com.example.Education.Controllers;

import com.example.Education.Product;
import com.example.Education.RestControllers.ProductController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/order")
public class OrderController {
    private final ProductController productController;
    @Autowired
    public OrderController(ProductController productController) {
        this.productController = productController;
    }

    @GetMapping
    public String showOrderPage(Model model) {
        List<Product> products = productController.readAllProducts();
        model.addAttribute("products", products);
        return "order";
    }
}
