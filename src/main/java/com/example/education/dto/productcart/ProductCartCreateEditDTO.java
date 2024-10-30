package com.example.education.dto.productcart;

import com.example.education.entity.CartEntity;
import com.example.education.entity.ProductEntity;
import lombok.Data;
import lombok.experimental.FieldNameConstants;

@Data
@FieldNameConstants
public class ProductCartCreateEditDTO {
    ProductEntity productid;
    CartEntity cartid;
    Long quantity;
}
