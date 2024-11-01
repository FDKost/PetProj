package com.example.education.services.productsinorder;

import com.example.education.dto.productsinorder.ProductsInOrderCreateEditDTO;
import com.example.education.dto.productsinorder.ProductsInOrderReadDTO;
import com.example.education.mapper.productsinorder.ProductsInOrderCreateEditMapper;
import com.example.education.mapper.productsinorder.ProductsInOrderReadMapper;
import com.example.education.repositories.ProductsInOrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RequiredArgsConstructor
@Service
@Transactional
public class ProductsInOrderServiceImpl implements ProductsInOrderService{
    private final ProductsInOrderRepository productsInOrderRepository;
    private final ProductsInOrderCreateEditMapper productsInOrderCreateEditMapper;
    private final ProductsInOrderReadMapper productsInOrderReadMapper;

    @Override
    public Optional<ProductsInOrderReadDTO> findById(UUID id){
        return productsInOrderRepository.findById(id)
                .map(productsInOrderReadMapper::map);
    }

    @Override
    public List<ProductsInOrderReadDTO> getAllProductsInOrder(){
        return productsInOrderRepository.findAllBy().stream()
                .map(productsInOrderReadMapper::map)
                .toList();
    }

    @Override
    public ProductsInOrderReadDTO create(ProductsInOrderCreateEditDTO dto){
        return Optional.of(dto)
                .map(productsInOrderCreateEditMapper::map)
                .map(productsInOrderRepository::save)
                .map(productsInOrderReadMapper::map)
                .orElseThrow();
    }

    @Override
    public Optional<ProductsInOrderReadDTO> update(UUID id, ProductsInOrderCreateEditDTO dto){
        return productsInOrderRepository.findById(id)
                .map(entity -> productsInOrderCreateEditMapper.map(dto,entity))
                .map(productsInOrderRepository::saveAndFlush)
                .map(productsInOrderReadMapper::map);
    }

    @Override
    public boolean delete(UUID id){
        return productsInOrderRepository.findById(id)
                .map(entity -> {
                    productsInOrderRepository.delete(entity);
                    productsInOrderRepository.flush();
                    return true;
                })
                .orElse(false);
    }
}
