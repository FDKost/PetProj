package com.example.Education;

import lombok.Data;

@Data
public class Payment {
    private Long id_payment;
    private Long total;
    private String check_URL;
    private Long id_user;
}