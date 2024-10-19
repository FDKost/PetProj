package com.example.education.dto.payment;

import com.example.education.entity.User;
import lombok.Value;

import java.util.UUID;

@Value
public class PaymentReadDTO {
    UUID id;
    Long total;
    String checkurl;
    User userid;
}
