package com.example.education.mapper.productcart;

import com.example.education.dto.productcart.ProductCartCreateEditDTO;
import com.example.education.entity.ProductCartEntity;
import com.example.education.mapper.Mapper;
import org.springframework.stereotype.Component;

@Component
public class ProductCartCreateEditMapper implements Mapper<ProductCartCreateEditDTO, ProductCartEntity> {
    @Override
    public ProductCartEntity map(ProductCartCreateEditDTO object) {
        ProductCartEntity productCart = new ProductCartEntity();
        fillProductCartCreateEditDTO(object, productCart);
        return productCart;
    }

    @Override
    public ProductCartEntity map(ProductCartCreateEditDTO fromObject, ProductCartEntity toObject) {
        fillProductCartCreateEditDTO(fromObject, toObject);
        return toObject;
    }

    private void fillProductCartCreateEditDTO(ProductCartCreateEditDTO object, ProductCartEntity productCart) {
        productCart.setProduct(object.getProductid());
        productCart.setCart(object.getCartid());
        productCart.setQuantity(object.getQuantity());
    }
}
