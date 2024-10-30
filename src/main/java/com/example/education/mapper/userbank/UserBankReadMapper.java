package com.example.education.mapper.userbank;

import com.example.education.dto.userbank.UserBankReadDTO;
import com.example.education.entity.UserBankEntity;
import com.example.education.mapper.Mapper;
import org.springframework.stereotype.Component;

@Component
public class UserBankReadMapper implements Mapper<UserBankEntity, UserBankReadDTO> {
    @Override
    public UserBankReadDTO map(UserBankEntity object) {
        return new UserBankReadDTO(
                object.getId(),
                object.getUser(),
                object.getBank()
        );
    }
}
