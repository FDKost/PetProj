package com.example.education.dto.order;

import com.example.education.entity.AddressEntity;
import com.example.education.entity.PaymentEntity;
import com.example.education.entity.StatusEntity;
import com.example.education.entity.UserEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.time.LocalDate;
import java.util.UUID;

@RequiredArgsConstructor
@AllArgsConstructor
@Data
public class OrderReadDTO {
    UUID id;
    UserEntity userid;
    PaymentEntity paymentid;
    AddressEntity addressid;
    LocalDate date;
    StatusEntity status;
}
