package com.example.education.dto.productsinorder;

import com.example.education.entity.OrderEntity;
import com.example.education.entity.ProductEntity;
import lombok.Value;
import lombok.experimental.FieldNameConstants;

@Value
@FieldNameConstants
public class ProductsInOrderCreateEditDTO {
    OrderEntity orderid;
    ProductEntity productid;
    Long quantity;
}
