package com.example.education.dto;

import lombok.Data;
import lombok.Value;


@Value
public class OrderRequestDTO {
    private Long[] productIds;
    private Integer[] quantities;
    private Double totalAmount;
}
