package com.example.education.controllers;

import com.example.consumingwebservice.wsdl.GetBankRequest;
import com.example.consumingwebservice.wsdl.GetBankResponse;
import com.example.education.client.BankClient;
import com.example.education.dto.order.OrderCreateEditDTO;
import com.example.education.dto.order.OrderReadDTO;
import com.example.education.dto.payment.PaymentCreateEditDTO;
import com.example.education.dto.productcart.ProductCartCreateEditDTO;
import com.example.education.dto.productcart.ProductCartReadDTO;
import com.example.education.services.kafka.SendProductsService;
import com.example.education.services.order.OrderService;
import com.example.education.services.productcart.ProductCartService;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/orders")
@RequiredArgsConstructor
public class OrderRestController {
    private final OrderService orderService;
    private final BankClient bankClient;
    private final SendProductsService sendProductsService;
    private final ProductCartService productCartService;

    @SneakyThrows
    @PostMapping("/createOrder")
    public ModelAndView createOrder(OrderCreateEditDTO orderCreateEditDTO,
                                    ProductCartCreateEditDTO productCartCreateEditDTO,
                                    PaymentCreateEditDTO paymentCreateEditDTO) {
        GetBankRequest request = new GetBankRequest();
        request.setSum(BigDecimal.valueOf(paymentCreateEditDTO.getTotal()));
        GetBankResponse response = bankClient.getBank(request, orderCreateEditDTO.getUserid(),"Tom",
                BigDecimal.valueOf(paymentCreateEditDTO.getTotal()));

        if(response.getStatus() == 200){
            sendProductsService.sendProducts(productCartCreateEditDTO.getCartid().getId());
            orderService.fillCreateOrder(orderCreateEditDTO, productCartCreateEditDTO, paymentCreateEditDTO);
            return new ModelAndView("redirect:/home");
        }else {
            return new ModelAndView("redirect:/cart");
        }
    }

    @GetMapping("/readOrder/{orderId}")
    public Optional<OrderReadDTO> readOrder(@PathVariable UUID orderId) {
        return  orderService.findById(orderId);
    }

    @PostMapping("/editOrder")
    public ModelAndView editOrder(@RequestParam UUID orderId,
                                  OrderCreateEditDTO orderCreateEditDTO) {
        orderService.update(orderId, orderCreateEditDTO);
        return new ModelAndView("redirect:/admin/orders");
    }

    @DeleteMapping("/deleteOrder/{orderId}")
    public void deleteOrder(@PathVariable UUID orderId) {
        orderService.delete(orderId);
    }

    @GetMapping("/getAllOrders")
    public List<OrderReadDTO> getAllOrders() {
        return orderService.getAllOrders();
    }
}
