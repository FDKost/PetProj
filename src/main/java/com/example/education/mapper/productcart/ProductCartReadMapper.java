package com.example.education.mapper.productcart;

import com.example.education.dto.productcart.ProductCartReadDTO;
import com.example.education.entity.ProductCart;
import com.example.education.mapper.Mapper;
import org.springframework.stereotype.Component;

@Component
public class ProductCartReadMapper implements Mapper<ProductCart, ProductCartReadDTO> {
    @Override
    public ProductCartReadDTO map(ProductCart object) {
        return new ProductCartReadDTO(
                object.getId(),
                object.getProduct(),
                object.getCart(),
                object.getQuantity());
    }
}
