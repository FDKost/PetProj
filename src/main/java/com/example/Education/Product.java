package com.example.Education;

import lombok.Data;

@Data
public class Product {
    private Long product_id;
    private String name;
    private Integer price;
    private String details;
    private String image_URL;
}
