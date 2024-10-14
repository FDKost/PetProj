package com.example.education.entity;

import lombok.Data;

@Data
public class ProductsInOrder {
    private Long orderItemId;
    private Long orderId;
    private Long productId;
    private Long quantity;
}
