package com.example.education.mapper.productsinorder;

import com.example.education.dto.productsinorder.ProductsInOrderCreateEditDTO;
import com.example.education.entity.ProductsInOrder;
import com.example.education.mapper.Mapper;
import org.springframework.stereotype.Component;

@Component
public class ProductsInOrderCreateEditMapper implements Mapper<ProductsInOrderCreateEditDTO, ProductsInOrder> {
    @Override
    public ProductsInOrder map(ProductsInOrderCreateEditDTO object) {
        ProductsInOrder productsInOrder = new ProductsInOrder();
        copy(object, productsInOrder);

        return productsInOrder;
    }

    @Override
    public ProductsInOrder map(ProductsInOrderCreateEditDTO fromObject, ProductsInOrder toObject) {
        copy(fromObject, toObject);

        return toObject;
    }

    private void copy(ProductsInOrderCreateEditDTO object, ProductsInOrder productsInOrder) {
        productsInOrder.setOrder(object.getOrderid());
        productsInOrder.setProduct(object.getProductid());
        productsInOrder.setQuantity(object.getQuantity());
    }
}
