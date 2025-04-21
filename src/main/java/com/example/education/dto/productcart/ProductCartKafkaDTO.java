package com.example.education.dto.productcart;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.UUID;

@Data
@AllArgsConstructor
public class ProductCartKafkaDTO {
    private UUID id;
    private UUID productId;
    private String productName;
    private Long price;
    private String details;
    private String imageURL;
    private UUID cartId;
    private Long quantity;
}
