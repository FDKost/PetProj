package com.example.education.controllers;

import com.example.education.dto.address.AddressReadDTO;
import com.example.education.dto.cart.CartReadDTO;
import com.example.education.dto.productcart.ProductCartReadDTO;
import com.example.education.dto.user.UserReadDTO;
import com.example.education.entity.StatusEntity;
import com.example.education.services.address.AddressServiceImpl;
import com.example.education.services.cart.CartServiceImpl;
import com.example.education.services.productcart.ProductCartServiceImpl;
import com.example.education.services.status.StatusServiceImpl;
import com.example.education.services.user.UserServiceImpl;
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

@Controller
@RequestMapping("/cart")
@AllArgsConstructor
public class CartController {
    private final UserServiceImpl userService;
    private final ProductCartServiceImpl productCartService;
    private final CartServiceImpl cartService;
    private final AddressServiceImpl addressService;
    private final StatusServiceImpl statusService;

    @GetMapping
    public String showCartPage(Model model,@AuthenticationPrincipal UserDetails userDetails) {
        String username = userDetails.getUsername();
        Optional<UserReadDTO> user = userService.findByUsername(username);
        if (user.isPresent()) {
            Optional<AddressReadDTO> userAddress = addressService.findAddressByUserId(user.get().getId());
            userAddress.ifPresent(addressReadDTO -> model.addAttribute("addressid", addressReadDTO.getId()));

            model.addAttribute("userid", user.get().getId());
            Optional<CartReadDTO> cart = cartService.findCartByUserId(user.get().getId());
            if(cart.isPresent()) {
                List<ProductCartReadDTO> cartItems = productCartService.findAllProductCartByCartId(cart.get().getId());
                long totalAmount = 0L;
                for (ProductCartReadDTO cartItem : cartItems) {
                    totalAmount += cartItem.getProductid().getPrice() * cartItem.getQuantity();
                }
                model.addAttribute("totalAmount", totalAmount);
                model.addAttribute("cartItems", cartItems);
            }
        }
        model.addAttribute("date", LocalDate.now());
        model.addAttribute("checkurl","https://drive.google.com/thumbnail?id=1SK68T2d4rLGx8_VC-EuFFyhIWorpLIu_");

        Optional<StatusEntity> status = statusService.findByDescription("In process");
        status.ifPresent(statusEntity -> model.addAttribute("status", statusEntity.getId()));

        return "cart";
    }
}
