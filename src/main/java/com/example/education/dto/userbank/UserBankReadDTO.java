package com.example.education.dto.userbank;

import com.example.education.entity.BankEntity;
import com.example.education.entity.UserEntity;
import lombok.Value;

import java.util.UUID;

@Value
public class UserBankReadDTO {
    UUID id;
    UserEntity user;
    BankEntity bank;
}
