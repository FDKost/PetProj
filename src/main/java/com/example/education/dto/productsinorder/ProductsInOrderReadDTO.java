package com.example.education.dto.productsinorder;

import com.example.education.entity.OrderEntity;
import com.example.education.entity.ProductEntity;
import lombok.Value;

import java.util.UUID;

@Value
public class ProductsInOrderReadDTO {
    UUID id;
    OrderEntity orderid;
    ProductEntity productid;
    Long quantity;
}
