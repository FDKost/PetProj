package com.example.education.entity;

import lombok.Data;

import java.util.Date;

@Data
public class Order {
    private Long orderId;
    private Long userId;
    private Long paymentId;
    private Long addressId;
    private Date date;
    private Long status;
}
