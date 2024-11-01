package com.example.education.controllers;

import com.example.education.dto.productcart.ProductCartCreateEditDTO;
import com.example.education.dto.productcart.ProductCartReadDTO;
import com.example.education.services.productcart.ProductCartService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Lazy;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.UUID;

@RestController
public class ProductCartController {
    @Qualifier("productCartServiceImpl")
    private final ProductCartService productCartService;

    public ProductCartController(@Lazy ProductCartService productCartService) {
        this.productCartService = productCartService;
    }

    @PostMapping("/api/createProductCart")
    public ModelAndView createProductCart(ProductCartCreateEditDTO productCartCreateEditDTO) {
        productCartService.fillCreateProductCart(productCartCreateEditDTO);

        return new ModelAndView("redirect:/order");
    }

    @GetMapping("/api/getProductCart")
    public List<ProductCartReadDTO> readProductCart(@RequestParam UUID cartItemId){
        return productCartService.findAllProductCartByCartId(cartItemId);
    }

    @PutMapping("/api/editProductCart")
    public void editProductCart(@RequestParam UUID productCartId,ProductCartCreateEditDTO productCartCreateEditDTO){
        productCartService.update(productCartId, productCartCreateEditDTO);
    }

    @PostMapping("/api/deleteProductCart")
    public ModelAndView deleteProductCart(@RequestParam UUID productId){
        productCartService.deleteProductFromProductCart(productId);

        return new ModelAndView("redirect:/cart");
    }
}
