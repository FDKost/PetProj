package com.example.education.dto.userbank;

import com.example.education.entity.BankEntity;
import com.example.education.entity.UserEntity;
import lombok.Value;
import org.springframework.ui.Model;

import java.util.UUID;

@Value
public class UserBankReadDTO {
    UUID id;
    UserEntity user;
    BankEntity bank;

    public Model populateBankModel(Model model) {
        model.addAttribute("bankId",bank.getId());

        return model;
    }

    public Model populateUserBankModel(Model model) {
        model.addAttribute("userBank",id);

        return model;
    }
}
