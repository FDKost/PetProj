package com.example.education.services.userbank;

import com.example.education.dto.userbank.UserBankCreateEditDTO;
import com.example.education.dto.userbank.UserBankReadDTO;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.ui.Model;

import java.util.Optional;
import java.util.UUID;

public interface UserBankService {
    Optional<UserBankReadDTO> findByBankId(UUID bankId);

    Optional<UserBankReadDTO> findByUserId(UUID userId);
    UserBankReadDTO create(UserBankCreateEditDTO userBankCreateEditDTO);

    Optional<UserBankReadDTO> update(UUID id, UserBankCreateEditDTO userBankCreateEditDTO);

    Boolean delete(UUID id);

    void fillProfile(UserDetails userDetails, Model model);
}
