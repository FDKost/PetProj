package com.example.education.repositories;

import com.example.education.entity.Cart;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface CartRepository extends JpaRepository<Cart, UUID> {

    Optional<Cart> findCartById(UUID id);

    Optional<Cart> findCartByUserId(UUID userId);
}
