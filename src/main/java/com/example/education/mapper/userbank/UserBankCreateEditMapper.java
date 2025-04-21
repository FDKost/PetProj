package com.example.education.mapper.userbank;

import com.example.education.dto.userbank.UserBankCreateEditDTO;
import com.example.education.entity.UserBankEntity;
import com.example.education.mapper.Mapper;
import org.springframework.stereotype.Component;

@Component
public class UserBankCreateEditMapper implements Mapper<UserBankCreateEditDTO, UserBankEntity> {
    @Override
    public UserBankEntity map(UserBankCreateEditDTO object) {
        UserBankEntity userBank = new UserBankEntity();
        fillUserBankCreateEditDTO(object, userBank);
        return userBank;
    }

    @Override
    public UserBankEntity map(UserBankCreateEditDTO fromObject, UserBankEntity toObject) {
        fillUserBankCreateEditDTO(fromObject, toObject);
        return toObject;
    }

    private void fillUserBankCreateEditDTO(UserBankCreateEditDTO object, UserBankEntity userBank) {
        userBank.setBank(object.getBankId());
        userBank.setUser(object.getUserId());
    }
}
