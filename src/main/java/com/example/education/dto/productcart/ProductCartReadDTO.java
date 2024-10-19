package com.example.education.dto.productcart;

import com.example.education.entity.Cart;
import com.example.education.entity.Product;
import lombok.Value;

import java.util.UUID;

@Value
public class ProductCartReadDTO {
    UUID id;
    Product productid;
    Cart cartid;
    Long quantity;
}
