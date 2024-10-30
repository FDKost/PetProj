package com.example.education.mapper.product;

import com.example.education.dto.product.ProductCreateEditDTO;
import com.example.education.entity.ProductEntity;
import com.example.education.mapper.Mapper;
import org.springframework.stereotype.Component;

@Component
public class ProductCreateEditMapper implements Mapper<ProductCreateEditDTO, ProductEntity> {
    @Override
    public ProductEntity map(ProductCreateEditDTO object) {
        ProductEntity product = new ProductEntity();
        fillProductCreateEditDTO(object, product);
        return product;
    }

    @Override
    public ProductEntity map(ProductCreateEditDTO fromObject, ProductEntity toObject) {
        fillProductCreateEditDTO(fromObject, toObject);

        return toObject;
    }

    private void fillProductCreateEditDTO(ProductCreateEditDTO object, ProductEntity product) {
        product.setName(object.getName());
        product.setPrice(product.getPrice());
        product.setDetails(object.getDetails());
        product.setImageURL(object.getImageurl());
    }
}
