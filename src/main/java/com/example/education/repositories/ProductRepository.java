package com.example.education.repositories;

import com.example.education.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface ProductRepository extends JpaRepository<Product, UUID> {

    Optional<Product> findProductById(UUID id);

    Optional<Product> findProductByName(String name);

    List<Product> findAllBy();
}
