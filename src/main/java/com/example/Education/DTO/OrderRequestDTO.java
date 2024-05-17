package com.example.Education.DTO;

import lombok.Data;

@Data
public class OrderRequestDTO {
    private Long[] productIds;
    private Integer[] quantities;
    private Double totalAmount;
}
