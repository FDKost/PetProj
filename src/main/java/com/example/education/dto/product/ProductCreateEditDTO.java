package com.example.education.dto.product;

import lombok.Value;
import lombok.experimental.FieldNameConstants;

@Value
@FieldNameConstants
public class ProductCreateEditDTO {
    String name;
    Long price;
    String details;
    String imageurl;
}
