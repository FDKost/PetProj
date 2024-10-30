package com.example.education.mapper.productsinorder;

import com.example.education.dto.productsinorder.ProductsInOrderReadDTO;
import com.example.education.entity.ProductsInOrderEntity;
import com.example.education.mapper.Mapper;
import org.springframework.stereotype.Component;

@Component
public class ProductsInOrderReadMapper implements Mapper<ProductsInOrderEntity, ProductsInOrderReadDTO> {
    @Override
    public ProductsInOrderReadDTO map(ProductsInOrderEntity object) {
        return new ProductsInOrderReadDTO(
                object.getId(),
                object.getOrder(),
                object.getProduct(),
                object.getQuantity());
    }
}
