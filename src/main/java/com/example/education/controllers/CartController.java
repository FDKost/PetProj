package com.example.education.controllers;

import com.example.education.dto.cart.CartReadDTO;
import com.example.education.dto.productcart.ProductCartReadDTO;
import com.example.education.dto.user.UserReadDTO;
import com.example.education.services.*;
import lombok.AllArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Controller
@RequestMapping("/cart")
@AllArgsConstructor
public class CartController {
    private final UserService userService;
    private final ProductCartService productCartService;
    private final CartService cartService;
    private final AddressService addressService;

    @GetMapping
    public String showCartPage(Model model,@AuthenticationPrincipal UserDetails userDetails) {
        String username = userDetails.getUsername();
        Optional<UserReadDTO> user = userService.findByUsername(username);
        Optional<CartReadDTO> cart = cartService.findCartByUserId(user.get().getId());
        List<ProductCartReadDTO> cartItems = productCartService.findAllProductCartByCartId(cart.get().getId());
        Long totalAmount = 0L;
        for (ProductCartReadDTO cartItem : cartItems) {
            totalAmount += cartItem.getProductid().getPrice() * cartItem.getQuantity();
        }
        model.addAttribute("cartItems", cartItems);
        model.addAttribute("totalAmount", totalAmount);
        model.addAttribute("userid", user.get().getId());
        /*model.addAttribute("paymentid",paymentService.create(new PaymentCreateEditDTO(totalAmount,
                    "https://drive.google.com/thumbnail?id=1SK68T2d4rLGx8_VC-EuFFyhIWorpLIu_",
                            new User(user.get().getId(), user.get().getLogin(),user.get().getPassword(),user.get().getRole()))).getId()); */
        model.addAttribute("date", LocalDate.now());
        model.addAttribute("checkurl","https://drive.google.com/thumbnail?id=1SK68T2d4rLGx8_VC-EuFFyhIWorpLIu_");
        model.addAttribute("status", UUID.fromString("f294afcb-af1d-4e82-b438-6666d963b24c"));
        model.addAttribute("addressid",addressService.findAddressByUserId(user.get().getId()).get().getId());

        return "cart";
    }
}
