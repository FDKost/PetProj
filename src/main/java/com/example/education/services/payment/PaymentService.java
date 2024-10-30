package com.example.education.services.payment;

import com.example.education.dto.payment.PaymentCreateEditDTO;
import com.example.education.dto.payment.PaymentReadDTO;

import java.util.Optional;
import java.util.UUID;

public interface PaymentService {
    Optional<PaymentReadDTO> findById(UUID id);

    PaymentReadDTO create(PaymentCreateEditDTO paymentDTO);

    Optional<PaymentReadDTO> update(UUID id, PaymentCreateEditDTO paymentDTO);

    boolean delete(UUID id);
}
