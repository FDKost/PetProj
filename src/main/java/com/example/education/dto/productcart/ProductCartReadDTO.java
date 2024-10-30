package com.example.education.dto.productcart;

import com.example.education.entity.CartEntity;
import com.example.education.entity.ProductEntity;
import lombok.Value;

import java.util.UUID;

@Value
public class ProductCartReadDTO {
    UUID id;
    ProductEntity productid;
    CartEntity cartid;
    Long quantity;
}
