package com.example.education.mapper.productsinorder;

import com.example.education.dto.productsinorder.ProductsInOrderReadDTO;
import com.example.education.entity.ProductsInOrder;
import com.example.education.mapper.Mapper;
import org.springframework.stereotype.Component;

@Component
public class ProductsInOrderReadMapper implements Mapper<ProductsInOrder, ProductsInOrderReadDTO> {
    @Override
    public ProductsInOrderReadDTO map(ProductsInOrder object) {
        return new ProductsInOrderReadDTO(
                object.getId(),
                object.getOrder(),
                object.getProduct(),
                object.getQuantity());
    }
}
