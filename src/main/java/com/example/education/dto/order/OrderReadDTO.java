package com.example.education.dto.order;

import com.example.education.entity.Address;
import com.example.education.entity.Payment;
import com.example.education.entity.User;
import lombok.Value;

import java.time.LocalDate;
import java.util.UUID;

@Value
public class OrderReadDTO {
    UUID id;
    User userid;
    Payment paymentid;
    Address addressid;
    LocalDate date;
    UUID status;
}
