package com.example.education.dto.productsinorder;

import com.example.education.entity.Order;
import com.example.education.entity.Product;
import lombok.Value;

import java.util.UUID;

@Value
public class ProductsInOrderReadDTO {
    UUID id;
    Order orderid;
    Product productid;
    Long quantity;
}
