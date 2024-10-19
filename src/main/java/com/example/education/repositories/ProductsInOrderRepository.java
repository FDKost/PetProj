package com.example.education.repositories;

import com.example.education.entity.ProductsInOrder;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ProductsInOrderRepository extends JpaRepository<ProductsInOrder, UUID> {

    Optional<ProductsInOrder> findProductsInOrderById(UUID id);

    List<ProductsInOrder> getAllProductsInOrder();

    void addProductsFromCart(UUID orderId,List<ProductsInOrder> cartItems);

    void deleteProductsInOrderById(UUID id);
}
