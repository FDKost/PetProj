package com.example.education.entity;

import lombok.Data;

@Data
public class ProductCart {
    private Long cartItemId;
    private Long productId;
    private Long cartId;
    private Long quantity;
    private Product product;
}
