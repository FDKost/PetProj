package com.example.education.repositories;

import com.example.education.entity.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface ProductRepository extends JpaRepository<ProductEntity, UUID> {

    Optional<ProductEntity> findProductById(UUID id);

    Optional<ProductEntity> findProductByName(String name);

    List<ProductEntity> findAllBy();
}
