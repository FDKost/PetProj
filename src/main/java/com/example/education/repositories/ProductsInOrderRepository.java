package com.example.education.repositories;

import com.example.education.entity.ProductsInOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface ProductsInOrderRepository extends JpaRepository<ProductsInOrder, UUID> {

    Optional<ProductsInOrder> findProductsInOrderById(UUID id);

    List<ProductsInOrder> findAllBy();

    void deleteProductsInOrderById(UUID id);

}
