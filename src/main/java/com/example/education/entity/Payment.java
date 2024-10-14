package com.example.education.entity;

import lombok.Data;

@Data
public class Payment {
    private Long paymentId;
    private Long total;
    private String checkURL;
    private Long userId;
}