package com.example.education.dto.userbank;

import com.example.education.entity.BankEntity;
import com.example.education.entity.UserEntity;
import lombok.Value;
import lombok.experimental.FieldNameConstants;

@Value
@FieldNameConstants
public class UserBankCreateEditDTO {
    UserEntity userId;
    BankEntity bankId;

}
