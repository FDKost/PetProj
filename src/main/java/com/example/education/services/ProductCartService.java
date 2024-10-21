package com.example.education.services;

import com.example.education.dto.productcart.ProductCartCreateEditDTO;
import com.example.education.dto.productcart.ProductCartReadDTO;
import com.example.education.mapper.productcart.ProductCartCreateEditMapper;
import com.example.education.mapper.productcart.ProductCartReadMapper;
import com.example.education.repositories.ProductCartRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RequiredArgsConstructor
@Service
@Transactional(readOnly = true)
public class ProductCartService {
    private final ProductCartRepository productCartRepository;
    private final ProductCartReadMapper productCartReadMapper;
    private final ProductCartCreateEditMapper productCartCreateEditMapper;

    public Optional<ProductCartReadDTO> findById(UUID id) {
        return productCartRepository.findById(id)
                .map(productCartReadMapper::map);
    }

    public Optional<ProductCartReadDTO> findProductCartByCartId(UUID cartId) {
        return productCartRepository.findProductCartByCartId(cartId)
                .map(productCartReadMapper::map);
    }

    public List<ProductCartReadDTO> findAllProductCartByCartId(UUID cartId) {
        return productCartRepository.findAllProductCartByCartId(cartId).stream()
                .map(productCartReadMapper::map)
                .toList();
    }



    @Transactional
    public ProductCartReadDTO create(ProductCartCreateEditDTO productCartDTO) {
        return Optional.of(productCartDTO)
                .map(productCartCreateEditMapper::map)
                .map(productCartRepository::save)
                .map(productCartReadMapper::map)
                .orElseThrow();
    }

    @Transactional
    public Optional<ProductCartReadDTO> update(UUID id, ProductCartCreateEditDTO productCartDTO) {
        return productCartRepository.findById(id)
                .map(entity -> productCartCreateEditMapper.map(productCartDTO,entity))
                .map(productCartRepository::saveAndFlush)
                .map(productCartReadMapper::map);
    }

    @Transactional
    public boolean delete(UUID id) {
        return productCartRepository.findById(id)
                .map(entity ->{
                    productCartRepository.delete(entity);
                    productCartRepository.flush();
                    return true;
                })
                .orElse(false);
    }

    @Transactional
    public void deleteAllFromProductCartByCartId(UUID cartId){
        productCartRepository.findAllProductCartByCartId(cartId)
                .forEach(entity -> {
                    productCartRepository.deleteAllByCartId(entity.getCart().getId());
                    productCartRepository.flush();
                });
    }

    @Transactional
    public boolean deleteProductFromProductCart(UUID productId) {
        return productCartRepository.findProductCartByProductId(productId)
                .map(entity -> {
                    productCartRepository.deleteByProductId(productId);
                    productCartRepository.flush();
                    return true;
                })
                .orElse(false);
    }
}
