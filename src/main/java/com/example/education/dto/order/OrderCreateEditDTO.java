package com.example.education.dto.order;

import com.example.education.entity.AddressEntity;
import com.example.education.entity.PaymentEntity;
import com.example.education.entity.StatusEntity;
import com.example.education.entity.UserEntity;
import lombok.Data;
import lombok.experimental.FieldNameConstants;

import java.time.LocalDate;

@Data
@FieldNameConstants
public class OrderCreateEditDTO {
    UserEntity userid;
    PaymentEntity paymentid;
    AddressEntity addressid;
    LocalDate date;
    StatusEntity status;
}
