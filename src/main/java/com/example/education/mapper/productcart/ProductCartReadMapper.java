package com.example.education.mapper.productcart;

import com.example.education.dto.productcart.ProductCartReadDTO;
import com.example.education.entity.ProductCartEntity;
import com.example.education.mapper.Mapper;
import org.springframework.stereotype.Component;

@Component
public class ProductCartReadMapper implements Mapper<ProductCartEntity, ProductCartReadDTO> {
    @Override
    public ProductCartReadDTO map(ProductCartEntity object) {
        return new ProductCartReadDTO(
                object.getId(),
                object.getProduct(),
                object.getCart(),
                object.getQuantity());
    }
}
