package com.example.education.repositories;

import com.example.education.entity.ProductCartEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface ProductCartRepository extends JpaRepository<ProductCartEntity, UUID> {

    Optional<ProductCartEntity> findProductCartById(UUID id);

    Optional<ProductCartEntity> findProductCartByCartId(UUID cartId);

    Optional<ProductCartEntity> findProductCartByProductId(UUID productId);

    List<ProductCartEntity> findAllProductCartByCartId(UUID cartId);

    void deleteAllByCartId(UUID cartId);

    void deleteByProductId(UUID productId);

    void deleteAllFromProductCartByCartId(UUID cartId);

    void deleteProductCartByCartIdAndProductId(UUID cartId, UUID productId);
}
