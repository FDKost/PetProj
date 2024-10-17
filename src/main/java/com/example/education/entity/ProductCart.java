package com.example.education.entity;

import lombok.Data;

import java.util.UUID;

@Data
public class ProductCart {
    private UUID id;
    private UUID productId;
    private UUID cartId;
    private Long quantity;
    private Product product;
}
