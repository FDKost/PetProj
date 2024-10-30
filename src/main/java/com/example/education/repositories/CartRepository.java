package com.example.education.repositories;

import com.example.education.entity.CartEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface CartRepository extends JpaRepository<CartEntity, UUID> {

    Optional<CartEntity> findCartById(UUID id);

    Optional<CartEntity> findCartByUserId(UUID userId);
}
