package com.example.education.entity;

import lombok.Data;

import java.util.Date;
import java.util.UUID;

@Data
public class Order {
    private UUID id;
    private UUID userId;
    private UUID paymentId;
    private UUID addressId;
    private Date date;
    private UUID status;
}
