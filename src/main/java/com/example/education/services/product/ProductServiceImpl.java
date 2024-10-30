package com.example.education.services.product;

import com.example.education.dto.product.ProductCreateEditDTO;
import com.example.education.dto.product.ProductReadDTO;
import com.example.education.mapper.product.ProductCreateEditMapper;
import com.example.education.mapper.product.ProductReadMapper;
import com.example.education.repositories.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RequiredArgsConstructor
@Service
@Transactional(readOnly = true)
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;
    private final ProductReadMapper productReadMapper;
    private final ProductCreateEditMapper productCreateEditMapper;

    @Override
    public Optional<ProductReadDTO> findById(UUID id) {
        return productRepository.findById(id)
                .map(productReadMapper::map);
    }

    @Override
    public Optional<ProductReadDTO> findProductByName(String name) {
        return productRepository.findProductByName(name)
                .map(productReadMapper::map);
    }

    @Override
    public List<ProductReadDTO> getAllProducts() {
        return productRepository.findAllBy().stream()
                .map(productReadMapper::map)
                .toList();
    }

    @Override
    @Transactional
    public ProductReadDTO create(ProductCreateEditDTO productDTO) {
        return Optional.of(productDTO)
                .map(productCreateEditMapper::map)
                .map(productRepository::save)
                .map(productReadMapper::map)
                .orElseThrow();
    }

    @Override
    @Transactional
    public Optional<ProductReadDTO> update(UUID id, ProductCreateEditDTO productDTO) {
        return productRepository.findById(id)
                .map(entity -> productCreateEditMapper.map(productDTO,entity))
                .map(productRepository::saveAndFlush)
                .map(productReadMapper::map);
    }

    @Override
    @Transactional
    public boolean delete(UUID id) {
        return productRepository.findById(id)
                .map(entity -> {
                    productRepository.delete(entity);
                    productRepository.flush();
                    return true;
                })
                .orElse(false);
    }
}
