package com.example.education.controllers;

import com.example.education.dto.payment.PaymentCreateEditDTO;
import com.example.education.dto.payment.PaymentReadDTO;
import com.example.education.services.payment.PaymentServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.UUID;

@RestController
@AllArgsConstructor
@RequestMapping("/payment")
public class PaymentRestController {
    private final PaymentServiceImpl paymentService;

    @PostMapping
    public PaymentReadDTO createPayment(PaymentCreateEditDTO paymentCreateEditDTO) {
        return paymentService.create(paymentCreateEditDTO);
    }

    @GetMapping
    public Optional<PaymentReadDTO> readPayment(@RequestParam UUID paymentId){
        return paymentService.findById(paymentId);
    }

    @PutMapping
    public void editPayment(PaymentCreateEditDTO paymentCreateEditDTO,
                            @RequestParam UUID paymentId){
        paymentService.update(paymentId, paymentCreateEditDTO);
    }

    @DeleteMapping
    public void deletePayment(@RequestParam UUID paymentId){
        paymentService.delete(paymentId);
    }
}
