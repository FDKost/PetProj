package com.example.education.controllers.restControllers;

import com.example.education.dao.PaymentDao;
import com.example.education.entity.Payment;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("/payment")
public class PaymentRestController {
    private final PaymentDao paymentDao;

    @PostMapping
    public Long createPayment(@RequestBody Payment payment){
        return paymentDao.createPayment(payment);
    }

    @GetMapping
    public Payment readPayment(@RequestParam Long paymentId){
        return paymentDao.getPaymentById(paymentId);
    }

    @PutMapping
    public void editPayment(@RequestBody Payment payment){
        paymentDao.editPayment(payment);
    }

    @DeleteMapping
    public void deletePayment(@RequestParam long paymentId){
        paymentDao.deletePayment(paymentId);
    }
}
