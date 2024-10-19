package com.example.education.dto;

import lombok.Value;

import java.util.UUID;


@Value
public class OrderRequestDTO {
    private UUID[] productIds;
    private Integer[] quantities;
    private Double totalAmount;
}
