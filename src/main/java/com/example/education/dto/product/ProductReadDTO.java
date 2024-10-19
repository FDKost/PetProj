package com.example.education.dto.product;

import lombok.Value;

import java.util.UUID;

@Value
public class ProductReadDTO {
    UUID id;
    String name;
    Long price;
    String details;
    String imageurl;
}
