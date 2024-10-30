package com.example.education.repositories;

import com.example.education.entity.ProductsInOrderEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface ProductsInOrderRepository extends JpaRepository<ProductsInOrderEntity, UUID> {

    Optional<ProductsInOrderEntity> findProductsInOrderById(UUID id);

    List<ProductsInOrderEntity> findAllBy();

    void deleteProductsInOrderById(UUID id);

}
