package com.example.education.entity;

import lombok.Data;

import java.util.UUID;

@Data
public class ProductsInOrder {
    private UUID id;
    private UUID orderId;
    private UUID productId;
    private Long quantity;
}
