package com.example.education.mapper.payment;

import com.example.education.dto.payment.PaymentReadDTO;
import com.example.education.entity.Payment;
import com.example.education.mapper.Mapper;
import org.springframework.stereotype.Component;

@Component
public class PaymentReadMapper implements Mapper<Payment, PaymentReadDTO> {
    @Override
    public PaymentReadDTO map(Payment object) {
        return new PaymentReadDTO(
                object.getId(),
                object.getTotal(),
                object.getCheckURL(),
                object.getUser());
    }
}
