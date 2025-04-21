package com.example.education.mapper.payment;

import com.example.education.dto.payment.PaymentCreateEditDTO;
import com.example.education.entity.PaymentEntity;
import com.example.education.mapper.Mapper;
import org.springframework.stereotype.Component;

@Component
public class PaymentCreateEditMapper implements Mapper<PaymentCreateEditDTO, PaymentEntity> {
    @Override
    public PaymentEntity map(PaymentCreateEditDTO object) {
        PaymentEntity payment = new PaymentEntity();
        fillPaymentCreateEditDTO(object, payment);
        return payment;
    }

    @Override
    public PaymentEntity map(PaymentCreateEditDTO fromObject, PaymentEntity toObject) {
        fillPaymentCreateEditDTO(fromObject, toObject);
        return toObject;
    }

    private void fillPaymentCreateEditDTO(PaymentCreateEditDTO object, PaymentEntity payment) {
        payment.setUser(object.getUserid());
        payment.setTotal(object.getTotal());
        payment.setCheckURL(object.getCheckurl());
    }
}
