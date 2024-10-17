package com.example.education.controllers.restControllers;

import com.example.education.dao.PaymentDao;
import com.example.education.entity.Payment;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@AllArgsConstructor
@RequestMapping("/payment")
public class PaymentRestController {
    private final PaymentDao paymentDao;

    @PostMapping
    public UUID createPayment(@RequestBody Payment payment){
        return paymentDao.createPayment(payment);
    }

    @GetMapping
    public Payment readPayment(@RequestParam UUID paymentId){
        return paymentDao.getPaymentById(paymentId);
    }

    @PutMapping
    public void editPayment(@RequestBody Payment payment){
        paymentDao.editPayment(payment);
    }

    @DeleteMapping
    public void deletePayment(@RequestParam UUID paymentId){
        paymentDao.deletePayment(paymentId);
    }
}
