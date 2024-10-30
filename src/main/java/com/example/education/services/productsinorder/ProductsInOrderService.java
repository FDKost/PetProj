package com.example.education.services.productsinorder;

import com.example.education.dto.productsinorder.ProductsInOrderCreateEditDTO;
import com.example.education.dto.productsinorder.ProductsInOrderReadDTO;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ProductsInOrderService {
    Optional<ProductsInOrderReadDTO> findById(UUID id);

    List<ProductsInOrderReadDTO> getAllProductsInOrder();

    ProductsInOrderReadDTO create(ProductsInOrderCreateEditDTO dto);

    Optional<ProductsInOrderReadDTO> update(UUID id, ProductsInOrderCreateEditDTO dto);

    boolean delete(UUID id);
}
