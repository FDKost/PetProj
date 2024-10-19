package com.example.education.mapper.product;

import com.example.education.dto.product.ProductCreateEditDTO;
import com.example.education.entity.Product;
import com.example.education.mapper.Mapper;
import org.springframework.stereotype.Component;

@Component
public class ProductCreateEditMapper implements Mapper<ProductCreateEditDTO, Product> {
    @Override
    public Product map(ProductCreateEditDTO object) {
        Product product = new Product();
        copy(object, product);
        return product;
    }

    @Override
    public Product map(ProductCreateEditDTO fromObject, Product toObject) {
        copy(fromObject, toObject);

        return toObject;
    }

    private void copy(ProductCreateEditDTO object, Product product) {
        product.setName(object.getName());
        product.setPrice(product.getPrice());
        product.setDetails(object.getDetails());
        product.setImageURL(object.getImageurl());
    }
}
