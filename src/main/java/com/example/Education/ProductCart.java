package com.example.Education;

import lombok.Data;

@Data
public class ProductCart {
    private Long cart_item_id;
    private Long product_id;
    private Long id_cart;
    private Long quantity;
    private Product product;
}
