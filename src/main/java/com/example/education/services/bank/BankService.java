package com.example.education.services.bank;

import com.example.education.entity.BankEntity;

import java.util.List;
import java.util.Optional;

public interface BankService {
    Optional<BankEntity> findBankEntityByBankName(String bankName);

    List<BankEntity> findAll();
}
