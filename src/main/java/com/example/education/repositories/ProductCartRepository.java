package com.example.education.repositories;

import com.example.education.entity.ProductCart;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ProductCartRepository extends JpaRepository<ProductCart, UUID> {

    Optional<ProductCart> findProductCartById(UUID id);

    Optional<ProductCart> findProductCartByCartId(UUID cartId);

    List<ProductCart> findAllProductCartByCartId(UUID cartId);

    void deleteAllFromProductCartByCartId(UUID cartId);

    void deleteProductCartByCartIdAndProductId(UUID cartId, UUID productId);
}
