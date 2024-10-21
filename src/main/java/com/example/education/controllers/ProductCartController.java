package com.example.education.controllers;

import com.example.education.dto.productcart.ProductCartCreateEditDTO;
import com.example.education.dto.productcart.ProductCartReadDTO;
import com.example.education.services.CartService;
import com.example.education.services.ProductCartService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.List;
import java.util.UUID;

@RestController
@AllArgsConstructor
public class ProductCartController {
    private final ProductCartService productCartService;
    private final CartService cartService;

    @PostMapping("/api/createProductCart")
    public ModelAndView createProductCart(ProductCartCreateEditDTO productCartCreateEditDTO) {
        productCartService.create(productCartCreateEditDTO);

        String orderUrl = ServletUriComponentsBuilder.fromCurrentContextPath().path("/order").toUriString();
        return new ModelAndView("redirect:" + orderUrl);
    }

    @GetMapping("/api/getProductCart")
    public List<ProductCartReadDTO> readProductCart(@RequestParam UUID cartItemId){
        productCartService.findAllProductCartByCartId(cartItemId);
        return productCartService.findAllProductCartByCartId(cartItemId);
    }

    @PutMapping("/api/editProductCart")
    public void editProductCart(@RequestParam UUID productCartId,@RequestBody ProductCartCreateEditDTO productCartCreateEditDTO){
        productCartService.update(productCartId, productCartCreateEditDTO);
    }

    @PostMapping("/api/deleteProductCart")
    public ModelAndView deleteProductCart(@RequestParam UUID productId){
        String cartUrl = ServletUriComponentsBuilder.fromCurrentContextPath().path("/cart").toUriString();
        productCartService.deleteProductFromProductCart(productId);
        return new ModelAndView("redirect:"+cartUrl);
    }
}
