package com.example.education.services.userbank;

import com.example.education.dto.userbank.UserBankCreateEditDTO;
import com.example.education.dto.userbank.UserBankReadDTO;
import com.example.education.mapper.userbank.UserBankCreateEditMapper;
import com.example.education.mapper.userbank.UserBankReadMapper;
import com.example.education.repositories.UserBankRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.UUID;

@RequiredArgsConstructor
@Service
@Transactional(readOnly = true)
public class UserBankServiceImpl implements UserBankService {
    private final UserBankRepository userBankRepository;
    private final UserBankReadMapper userBankReadMapper;
    private final UserBankCreateEditMapper userBankCreateEditMapper;
    @Override
    public Optional<UserBankReadDTO> findByBankId(UUID bankId) {
        return userBankRepository.findByBankId(bankId)
                .map(userBankReadMapper::map);
    }

    @Override
    public Optional<UserBankReadDTO> findByUserId(UUID userId) {
        return userBankRepository.findByUserId(userId)
                .map(userBankReadMapper::map);
    }

    @Override
    @Transactional
    public UserBankReadDTO create(UserBankCreateEditDTO userBankCreateEditDTO) {
        return Optional.of(userBankCreateEditDTO)
                .map(userBankCreateEditMapper::map)
                .map(userBankRepository::save)
                .map(userBankReadMapper::map)
                .orElseThrow();
    }

    @Override
    @Transactional
    public Optional<UserBankReadDTO> update(UUID userId, UserBankCreateEditDTO userBankCreateEditDTO) {
        return userBankRepository.findByUserId(userId)
                .map(entity -> userBankCreateEditMapper.map(userBankCreateEditDTO,entity))
                .map(userBankRepository::saveAndFlush)
                .map(userBankReadMapper::map);
    }

    @Override
    @Transactional
    public Boolean delete(UUID id) {
        return userBankRepository.findById(id)
                .map(entity -> {
                    userBankRepository.delete(entity);
                    userBankRepository.flush();
                    return true;
                })
                .orElse(false);
    }
}
