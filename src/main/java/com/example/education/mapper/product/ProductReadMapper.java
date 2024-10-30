package com.example.education.mapper.product;

import com.example.education.dto.product.ProductReadDTO;
import com.example.education.entity.ProductEntity;
import com.example.education.mapper.Mapper;
import org.springframework.stereotype.Component;

@Component
public class ProductReadMapper implements Mapper<ProductEntity, ProductReadDTO> {
    @Override
    public ProductReadDTO map(ProductEntity object) {
        return new ProductReadDTO(
                object.getId(),
                object.getName(),
                object.getPrice(),
                object.getDetails(),
                object.getImageURL()
        );
    }
}
