package com.example.Education.RestControllers;

import com.example.Education.DAO.PaymentDao;
import com.example.Education.Payment;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("/payment")
public class PaymentRestController {
    private final PaymentDao dao;

    @PostMapping
    public Long createPayment(@RequestBody Payment payment){
        return dao.createPayment(payment);
    }

    @GetMapping
    public Payment readPayment(@RequestParam Long id_payment){
        return dao.getPaymentById(id_payment);
    }

    @PutMapping
    public void editPayment(@RequestBody Payment payment){
        dao.editPayment(payment);
    }

    @DeleteMapping
    public void deletePayment(@RequestParam long id_payment){
        dao.deletePayment(id_payment);
    }
}
