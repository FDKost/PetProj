package com.example.education.dto.order;

import com.example.education.entity.Address;
import com.example.education.entity.Payment;
import com.example.education.entity.User;
import lombok.Value;
import lombok.experimental.FieldNameConstants;

import java.time.LocalDate;
import java.util.UUID;
@Value
@FieldNameConstants
public class OrderCreateEditDTO {
    User userid;
    Payment paymentid;
    Address addressid;
    LocalDate date;
    UUID status;
}
