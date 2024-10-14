package com.example.education.entity;

import lombok.Data;

@Data
public class Product {
    private Long productId;
    private String name;
    private Integer price;
    private String details;
    private String imageURL;
}
