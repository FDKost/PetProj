package com.example.education.services.order;

import com.example.education.dto.cart.CartCreateEditDTO;
import com.example.education.dto.cart.CartReadDTO;
import com.example.education.dto.order.OrderCreateEditDTO;
import com.example.education.dto.order.OrderReadDTO;
import com.example.education.dto.payment.PaymentCreateEditDTO;
import com.example.education.dto.payment.PaymentReadDTO;
import com.example.education.dto.product.ProductReadDTO;
import com.example.education.dto.productcart.ProductCartCreateEditDTO;
import com.example.education.dto.user.UserReadDTO;
import com.example.education.entity.PaymentEntity;
import com.example.education.entity.StatusEntity;
import com.example.education.entity.UserEntity;
import com.example.education.mapper.order.OrderCreateEditMapper;
import com.example.education.mapper.order.OrderReadMapper;
import com.example.education.repositories.OrderRepository;
import com.example.education.services.cart.CartService;
import com.example.education.services.payment.PaymentService;
import com.example.education.services.product.ProductService;
import com.example.education.services.productcart.ProductCartService;
import com.example.education.services.status.StatusService;
import com.example.education.services.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
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
public class OrderServiceImpl implements OrderService {
    private final OrderRepository orderRepository;
    private final OrderCreateEditMapper orderCreateEditMapper;
    private final OrderReadMapper orderReadMapper;
    @Qualifier("statusServiceImpl")
    private final StatusService statusService;
    @Qualifier("productServiceImpl")
    private final ProductService productService;
    @Qualifier("cartServiceImpl")
    private final CartService cartService;
    @Qualifier("userServiceImpl")
    private final UserService userService;
    @Qualifier("paymentServiceImpl")
    private final PaymentService paymentService;
    @Qualifier("productCartServiceImpl")
    private final ProductCartService productCartService;
    @Qualifier("orderServiceImpl")
    private final OrderService orderService;

    @Override
    public Optional<OrderReadDTO> findById(UUID id){
        return orderRepository.findById(id).map(orderReadMapper::map);
    }

    public List<OrderReadDTO> getAllOrders(){
        return orderRepository.findAllBy().stream()
                .map(orderReadMapper::map)
                .toList();
    }

    @Override
    public OrderReadDTO create(OrderCreateEditDTO orderDTO){
        return Optional.of(orderDTO)
                .map(orderCreateEditMapper::map)
                .map(orderRepository::save)
                .map(orderReadMapper::map)
                .orElseThrow();
    }

    @Override
    @Transactional
    public Optional<OrderReadDTO> update(UUID id, OrderCreateEditDTO orderDTO){
        Optional<OrderReadDTO> order = findById(id);
        if(order.isPresent()){
            return orderRepository.findById(id)
                    .map(entity -> orderCreateEditMapper.map(orderDTO, entity))
                    .map(orderRepository::saveAndFlush)
                    .map(orderReadMapper::map);
        }else {
            return Optional.empty();
        }
    }

    @Override
    @Transactional
    public boolean delete(UUID id){
        return orderRepository.findById(id)
                .map(entity -> {
                    orderRepository.delete(entity);
                    orderRepository.flush();
                    return true;
                })
                .orElse(false);
    }
    @Transactional
    public void fillManageOrders(Model model){
        List<OrderReadDTO> orders =getAllOrders();
        model.addAttribute("orders", orders);
        Optional<StatusEntity> statusComplete = statusService.findByDescription("Completed");

        statusComplete.ifPresent(statusEntity -> model.addAttribute("statusComplete", statusEntity.getId()));

        Optional<StatusEntity> statusInProcess = statusService.findByDescription("In process");
        statusInProcess.ifPresent(statusEntity -> model.addAttribute("statusPending", statusEntity.getId()));
    }

    @Transactional
    public void fillShowOrderPage(Model model, UserDetails userDetails){
        List<ProductReadDTO> products = productService.getAllProducts();
        Optional<UserReadDTO> user = userService.findByUsername(userDetails.getUsername());
        if (user.isPresent()) {
            Optional<CartReadDTO> existingCart=cartService.findCartByUserId(user.get().getId());
            if(existingCart.isPresent()){
                model.addAttribute("cartid", existingCart.get().getId());
            }else{
                UserEntity actualUser = new UserEntity(user.get().getId(),
                        user.get().getLogin(),
                        user.get().getPassword(),
                        user.get().getRole());
                CartReadDTO cart = cartService.create(new CartCreateEditDTO(actualUser, LocalDate.now()));
                model.addAttribute("cartid", cart.getId());
            }
        }
        model.addAttribute("products", products);
    }

    @Transactional
    public void fillCreateOrder(OrderCreateEditDTO orderCreateEditDTO,
                                ProductCartCreateEditDTO productCartCreateEditDTO,
                                PaymentCreateEditDTO paymentCreateEditDTO){
        PaymentReadDTO payment=paymentService.create(paymentCreateEditDTO);
        PaymentEntity actualPayment = new PaymentEntity(payment.getId(),
                                                        payment.getTotal(),
                                                        payment.getCheckurl(),
                                                        payment.getUserid());
        orderCreateEditDTO.setPaymentid(actualPayment);
        orderService.create(orderCreateEditDTO);
        productCartService.deleteAllFromProductCartByCartId(productCartCreateEditDTO.getCartid().getId());
    }

}
