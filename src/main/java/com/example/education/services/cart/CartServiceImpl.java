package com.example.education.services.cart;

import com.example.education.dto.address.AddressReadDTO;
import com.example.education.dto.cart.CartCreateEditDTO;
import com.example.education.dto.cart.CartReadDTO;
import com.example.education.dto.productcart.ProductCartReadDTO;
import com.example.education.dto.user.UserReadDTO;
import com.example.education.entity.StatusEntity;
import com.example.education.mapper.cart.CartCreateEditMapper;
import com.example.education.mapper.cart.CartReadMapper;
import com.example.education.repositories.CartRepository;
import com.example.education.services.address.AddressServiceImpl;
import com.example.education.services.productcart.ProductCartServiceImpl;
import com.example.education.services.status.StatusServiceImpl;
import com.example.education.services.user.UserServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RequiredArgsConstructor
@Service
@Transactional(readOnly = true)
public class CartServiceImpl implements CartService {
    private final CartRepository cartRepository;
    private final CartCreateEditMapper cartCreateEditMapper;
    private final CartReadMapper cartReadMapper;
    private final StatusServiceImpl statusService;
    private final AddressServiceImpl addressService;
    private final ProductCartServiceImpl productCartService;
    private final UserServiceImpl userService;

    @Override
    public Optional<CartReadDTO> findById(UUID id) {
        return cartRepository.findById(id)
                .map(cartReadMapper::map);
    }

    @Override
    public Optional<CartReadDTO> findCartByUserId(UUID userId) {
        return cartRepository.findCartByUserId(userId)
                .map(cartReadMapper::map);
    }

    @Override
    @Transactional
    public CartReadDTO create(CartCreateEditDTO cartDTO) {
        return Optional.of(cartDTO)
                .map(cartCreateEditMapper::map)
                .map(cartRepository::save)
                .map(cartReadMapper::map)
                .orElseThrow();
    }

    @Override
    @Transactional
    public Optional<CartReadDTO> update(UUID id,CartCreateEditDTO cartDTO) {
        return cartRepository.findById(id)
                .map(entity -> cartCreateEditMapper.map(cartDTO,entity))
                .map(cartRepository::saveAndFlush)
                .map(cartReadMapper::map);
    }

    @Override
    @Transactional
    public boolean delete(UUID id) {
        return cartRepository.findById(id)
                .map(entity -> {
                    cartRepository.delete(entity);
                    cartRepository.flush();
                    return true;
                })
                .orElse(false);
    }

    @Transactional
    public void fillShowCartPage(Model model, UserDetails userDetails){
        String username = userDetails.getUsername();
        Optional<UserReadDTO> user = userService.findByUsername(username);
        if (user.isPresent()) {
            Optional<AddressReadDTO> userAddress = addressService.findAddressByUserId(user.get().getId());
            userAddress.ifPresent(addressReadDTO -> model.addAttribute("addressid", addressReadDTO.getId()));

            model.addAttribute("userid", user.get().getId());
            Optional<CartReadDTO> cart = findCartByUserId(user.get().getId());
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
    }
}