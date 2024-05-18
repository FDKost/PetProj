package com.example.Education;

import lombok.Data;

import java.util.Date;

@Data
public class Order {
    private Long id_order;
    private Long id_user;
    private Long id_payment;
    private Long id_address;
    private Date date;
    private Long status;
}
