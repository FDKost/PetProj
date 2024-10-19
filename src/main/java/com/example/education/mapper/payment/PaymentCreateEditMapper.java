package com.example.education.mapper.payment;

import com.example.education.dto.payment.PaymentCreateEditDTO;
import com.example.education.entity.Payment;
import com.example.education.mapper.Mapper;
import org.springframework.stereotype.Component;

@Component
public class PaymentCreateEditMapper implements Mapper<PaymentCreateEditDTO, Payment> {
    @Override
    public Payment map(PaymentCreateEditDTO object) {
        Payment payment = new Payment();
        copy(object,payment);
        return payment;
    }

    @Override
    public Payment map(PaymentCreateEditDTO fromObject, Payment toObject) {
        copy(fromObject,toObject);
        return toObject;
    }

    private void copy(PaymentCreateEditDTO object, Payment payment) {
        payment.setUser(object.getUserid());
        payment.setTotal(object.getTotal());
        payment.setCheckURL(object.getCheckurl());
    }
}
