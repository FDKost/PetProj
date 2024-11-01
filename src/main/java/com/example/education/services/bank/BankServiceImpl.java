package com.example.education.services.bank;

import com.example.education.entity.BankEntity;
import com.example.education.repositories.BankRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
@Transactional(readOnly = true)
public class BankServiceImpl implements BankService {
    private final BankRepository bankRepository;

    @Override
    public Optional<BankEntity> findBankEntityByBankName(String bankName) {
        return bankRepository.findBankEntityByBankName(bankName);
    }
    @Override
    public List<BankEntity> findAll() {
        return bankRepository.findAll();
    }
}
