package com.example.education.dto.productcart;

import com.example.education.entity.Cart;
import com.example.education.entity.Product;
import lombok.Value;
import lombok.experimental.FieldNameConstants;

@Value
@FieldNameConstants
public class ProductCartCreateEditDTO {
    Product productid;
    Cart cartid;
    Long quantity;
}
