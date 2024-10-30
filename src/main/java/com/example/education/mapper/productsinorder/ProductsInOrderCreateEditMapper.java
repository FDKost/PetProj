package com.example.education.mapper.productsinorder;

import com.example.education.dto.productsinorder.ProductsInOrderCreateEditDTO;
import com.example.education.entity.ProductsInOrderEntity;
import com.example.education.mapper.Mapper;
import org.springframework.stereotype.Component;

@Component
public class ProductsInOrderCreateEditMapper implements Mapper<ProductsInOrderCreateEditDTO, ProductsInOrderEntity> {
    @Override
    public ProductsInOrderEntity map(ProductsInOrderCreateEditDTO object) {
        ProductsInOrderEntity productsInOrder = new ProductsInOrderEntity();
        fillProductsInOrderCreateEditDTO(object, productsInOrder);

        return productsInOrder;
    }

    @Override
    public ProductsInOrderEntity map(ProductsInOrderCreateEditDTO fromObject, ProductsInOrderEntity toObject) {
        fillProductsInOrderCreateEditDTO(fromObject, toObject);

        return toObject;
    }

    private void fillProductsInOrderCreateEditDTO(ProductsInOrderCreateEditDTO object, ProductsInOrderEntity productsInOrder) {
        productsInOrder.setOrder(object.getOrderid());
        productsInOrder.setProduct(object.getProductid());
        productsInOrder.setQuantity(object.getQuantity());
    }
}
