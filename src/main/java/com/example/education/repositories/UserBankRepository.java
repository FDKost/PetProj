package com.example.education.repositories;

import com.example.education.entity.UserBankEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface UserBankRepository extends JpaRepository<UserBankEntity, UUID> {
    Optional<UserBankEntity> findByBankId(UUID bankId);

    Optional<UserBankEntity> findByUserId(UUID userId);
}
