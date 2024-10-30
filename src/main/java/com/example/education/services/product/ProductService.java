package com.example.education.services.product;

import com.example.education.dto.product.ProductCreateEditDTO;
import com.example.education.dto.product.ProductReadDTO;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ProductService {
    Optional<ProductReadDTO> findById(UUID id);

    Optional<ProductReadDTO> findProductByName(String name);

    List<ProductReadDTO> getAllProducts();

    ProductReadDTO create(ProductCreateEditDTO productDTO);

    Optional<ProductReadDTO> update(UUID id, ProductCreateEditDTO productDTO);

    boolean delete(UUID id);
}
