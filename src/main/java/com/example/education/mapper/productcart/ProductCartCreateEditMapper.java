package com.example.education.mapper.productcart;

import com.example.education.dto.productcart.ProductCartCreateEditDTO;
import com.example.education.entity.ProductCart;
import com.example.education.mapper.Mapper;
import org.springframework.stereotype.Component;

@Component
public class ProductCartCreateEditMapper implements Mapper<ProductCartCreateEditDTO, ProductCart> {
    @Override
    public ProductCart map(ProductCartCreateEditDTO object) {
        ProductCart productCart = new ProductCart();
        copy(object, productCart);
        return productCart;
    }

    @Override
    public ProductCart map(ProductCartCreateEditDTO fromObject, ProductCart toObject) {
        copy(fromObject, toObject);
        return toObject;
    }

    private void copy(ProductCartCreateEditDTO object, ProductCart productCart) {
        productCart.setProduct(object.getProductid());
        productCart.setCart(object.getCartid());
        productCart.setQuantity(object.getQuantity());
    }
}
