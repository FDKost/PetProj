package com.example.education.services;

import com.example.education.dto.payment.PaymentCreateEditDTO;
import com.example.education.dto.payment.PaymentReadDTO;
import com.example.education.mapper.payment.PaymentCreateEditMapper;
import com.example.education.mapper.payment.PaymentReadMapper;
import com.example.education.repositories.PaymentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.UUID;

@RequiredArgsConstructor
@Service
@Transactional(readOnly = true)
public class PaymentService {
    private final PaymentRepository paymentRepository;
    private final PaymentCreateEditMapper paymentCreateEditMapper;
    private final PaymentReadMapper paymentReadMapper;

    public Optional<PaymentReadDTO> findById(UUID id){
        return paymentRepository.findById(id)
                .map(paymentReadMapper::map);
    }

    @Transactional
    public PaymentReadDTO create(PaymentCreateEditDTO paymentDTO){
        return Optional.of(paymentDTO)
                .map(paymentCreateEditMapper::map)
                .map(paymentRepository::save)
                .map(paymentReadMapper::map)
                .orElseThrow();
    }

    @Transactional
    public Optional<PaymentReadDTO> update(UUID id, PaymentCreateEditDTO paymentDTO){
        return paymentRepository.findById(id)
                .map(entity -> paymentCreateEditMapper.map(paymentDTO,entity))
                .map(paymentRepository::saveAndFlush)
                .map(paymentReadMapper::map);
    }

    @Transactional
    public boolean delete(UUID id){
        return paymentRepository.findById(id)
                .map(entity -> {
                    paymentRepository.delete(entity);
                    paymentRepository.flush();
                    return true;
                })
                .orElse(false);
    }

}
