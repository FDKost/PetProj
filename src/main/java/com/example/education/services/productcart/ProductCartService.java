package com.example.education.services.productcart;

import com.example.education.dto.productcart.ProductCartCreateEditDTO;
import com.example.education.dto.productcart.ProductCartReadDTO;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ProductCartService {
    Optional<ProductCartReadDTO> findById(UUID id);

    Optional<ProductCartReadDTO> findProductCartByCartId(UUID cartId);

    List<ProductCartReadDTO> findAllProductCartByCartId(UUID cartId);

    ProductCartReadDTO create(ProductCartCreateEditDTO productCartDTO);

    Optional<ProductCartReadDTO> update(UUID id, ProductCartCreateEditDTO productCartDTO);

    boolean delete(UUID id);

    void deleteAllFromProductCartByCartId(UUID cartId);

    boolean deleteProductFromProductCart(UUID productId);

    void fillCreateProductCart(ProductCartCreateEditDTO productCartCreateEditDTO);
}
