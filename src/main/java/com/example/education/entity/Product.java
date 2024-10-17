package com.example.education.entity;

import lombok.Data;

import java.util.UUID;

@Data
public class Product {
    private UUID id;
    private String name;
    private Integer price;
    private String details;
    private String imageURL;
}
