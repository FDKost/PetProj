package com.example.education.services.userbank;

import com.example.education.dto.user.UserReadDTO;
import com.example.education.dto.userbank.UserBankCreateEditDTO;
import com.example.education.dto.userbank.UserBankReadDTO;
import com.example.education.entity.BankEntity;
import com.example.education.entity.UserEntity;
import com.example.education.mapper.userbank.UserBankCreateEditMapper;
import com.example.education.mapper.userbank.UserBankReadMapper;
import com.example.education.repositories.UserBankRepository;
import com.example.education.services.bank.BankService;
import com.example.education.services.user.UserService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@Transactional(readOnly = true)
public class UserBankServiceImpl implements UserBankService {
    private final UserBankRepository userBankRepository;
    private final UserBankReadMapper userBankReadMapper;
    private final UserBankCreateEditMapper userBankCreateEditMapper;
    @Qualifier("userServiceImpl")
    private final UserService userService;
    @Qualifier("bankServiceImpl")
    private final BankService bankService;
    @Qualifier("userBankServiceImpl")
    private final UserBankService userBankService;

    public UserBankServiceImpl(UserBankRepository userBankRepository,
                               UserBankReadMapper userBankReadMapper,
                               UserBankCreateEditMapper userBankCreateEditMapper,
                               @Lazy UserService userService,
                               @Lazy BankService bankService,
                               @Lazy UserBankService userBankService) {
        this.userBankRepository = userBankRepository;
        this.userBankReadMapper = userBankReadMapper;
        this.userBankCreateEditMapper = userBankCreateEditMapper;
        this.userService = userService;
        this.bankService = bankService;
        this.userBankService = userBankService;
    }

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

    @Transactional
    public void fillProfile(UserDetails userDetails, Model model) {
        String login = userDetails.getUsername();
        Optional<UserReadDTO> user = userService.findByUsername(login);
        String arasakaName = "Arasaka";
        Optional<BankEntity> arasakaBank = bankService.findBankEntityByBankName(arasakaName);
        if(user.isPresent()) {
            user.get().populateUserModel(model);

            Optional<UserBankReadDTO> userBank = userBankService.findByUserId(user.get().getId());
            userBank.ifPresent(userBankReadDTO -> userBankReadDTO.populateBankModel(model));

            List<BankEntity> banks = bankService.findAll();
            if (!banks.isEmpty()) {
                for (BankEntity bankEntity : banks) {
                    model.addAttribute(bankEntity.getBankName(),bankEntity.getId());
                }
            }

            if (userBankService.findByUserId(user.get().getId()).isPresent()) {
                userBank.ifPresent(userBankReadDTO -> userBankReadDTO.populateUserBankModel(model));
            } else {
                arasakaBank.ifPresent(bankEntity -> model.addAttribute("userBank", userBankService.create(new UserBankCreateEditDTO(
                        new UserEntity(user.get().getId(),
                                user.get().getLogin(),
                                user.get().getPassword(),
                                user.get().getRole()
                        ),
                        bankEntity
                ))));
            }
        }
    }
}
