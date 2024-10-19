package com.example.education.repositories;

import com.example.education.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ProductRepository extends JpaRepository<Product, UUID> {

    Optional<Product> findProductById(UUID id);

    Optional<Product> findProductByName(String name);

    List<Product> getAllProducts();
}
