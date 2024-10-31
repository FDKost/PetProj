package com.example.education.repositories;

import com.example.education.entity.BankEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface BankRepository extends JpaRepository<BankEntity, UUID> {
    Optional<BankEntity> findBankEntityByBankName(String bankName);
}
