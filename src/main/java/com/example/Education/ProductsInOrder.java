package com.example.Education;

import lombok.Data;

@Data
public class ProductsInOrder {
    private Long order_item_id;
    private Long id_order;
    private Long product_id;
    private Long quantity;
}
