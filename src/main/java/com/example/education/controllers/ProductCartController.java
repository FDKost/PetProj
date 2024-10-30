package com.example.education.controllers;

import com.example.education.dto.productcart.ProductCartCreateEditDTO;
import com.example.education.dto.productcart.ProductCartReadDTO;
import com.example.education.services.productcart.ProductCartServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.UUID;

@RestController
@AllArgsConstructor
public class ProductCartController {
    private final ProductCartServiceImpl productCartService;

    @PostMapping("/api/createProductCart")
    public ModelAndView createProductCart(ProductCartCreateEditDTO productCartCreateEditDTO) {
        List<ProductCartReadDTO> carts = productCartService.findAllProductCartByCartId(productCartCreateEditDTO.getCartid().getId());
        UUID productId = null;
        Long quantity = 0L;
        for (ProductCartReadDTO productCartReadDTO : carts) {
            if(productCartCreateEditDTO.getProductid().getId().equals(productCartReadDTO.getProductid().getId())) {
                productId = productCartReadDTO.getProductid().getId();
                quantity += productCartReadDTO.getQuantity();
            }
        }
        if (quantity == 0){
            productCartService.create(productCartCreateEditDTO);
        }else {
            productCartService.deleteProductFromProductCart(productId);
            productCartCreateEditDTO.setQuantity(quantity+productCartCreateEditDTO.getQuantity());
            productCartService.create(productCartCreateEditDTO);
        }
        return new ModelAndView("redirect:/order");
    }

    @GetMapping("/api/getProductCart")
    public List<ProductCartReadDTO> readProductCart(@RequestParam UUID cartItemId){
        productCartService.findAllProductCartByCartId(cartItemId);
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
