package com.example.education.repositories;

import com.example.education.entity.Product;
import com.example.education.entity.ProductCart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface ProductCartRepository extends JpaRepository<ProductCart, UUID> {

    Optional<ProductCart> findProductCartById(UUID id);

    Optional<ProductCart> findProductCartByCartId(UUID cartId);

    Optional<ProductCart> findProductCartByProductId(UUID productId);

    List<ProductCart> findAllProductCartByCartId(UUID cartId);

    void deleteAllByCartId(UUID cartId);

    void deleteByProductId(UUID productId);

    void deleteAllFromProductCartByCartId(UUID cartId);

    void deleteProductCartByCartIdAndProductId(UUID cartId, UUID productId);
}
