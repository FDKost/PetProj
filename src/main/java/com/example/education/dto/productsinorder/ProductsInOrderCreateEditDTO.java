package com.example.education.dto.productsinorder;

import com.example.education.entity.Order;
import com.example.education.entity.Product;
import lombok.Value;
import lombok.experimental.FieldNameConstants;

@Value
@FieldNameConstants
public class ProductsInOrderCreateEditDTO {
    Order orderid;
    Product productid;
    Long quantity;
}
