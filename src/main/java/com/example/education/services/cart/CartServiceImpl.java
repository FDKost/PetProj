package com.example.education.services.cart;

import com.example.education.dto.cart.CartCreateEditDTO;
import com.example.education.dto.cart.CartReadDTO;
import com.example.education.mapper.cart.CartCreateEditMapper;
import com.example.education.mapper.cart.CartReadMapper;
import com.example.education.repositories.CartRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.UUID;

@RequiredArgsConstructor
@Service
@Transactional(readOnly = true)
public class CartServiceImpl implements CartService {
    private final CartRepository cartRepository;
    private final CartCreateEditMapper cartCreateEditMapper;
    private final CartReadMapper cartReadMapper;

    @Override
    public Optional<CartReadDTO> findById(UUID id) {
        return cartRepository.findById(id)
                .map(cartReadMapper::map);
    }

    @Override
    public Optional<CartReadDTO> findCartByUserId(UUID userId) {
        return cartRepository.findCartByUserId(userId)
                .map(cartReadMapper::map);
    }

    @Override
    @Transactional
    public CartReadDTO create(CartCreateEditDTO cartDTO) {
        return Optional.of(cartDTO)
                .map(cartCreateEditMapper::map)
                .map(cartRepository::save)
                .map(cartReadMapper::map)
                .orElseThrow();
    }

    @Override
    @Transactional
    public Optional<CartReadDTO> update(UUID id,CartCreateEditDTO cartDTO) {
        return cartRepository.findById(id)
                .map(entity -> cartCreateEditMapper.map(cartDTO,entity))
                .map(cartRepository::saveAndFlush)
                .map(cartReadMapper::map);
    }

    @Override
    @Transactional
    public boolean delete(UUID id) {
        return cartRepository.findById(id)
                .map(entity -> {
                    cartRepository.delete(entity);
                    cartRepository.flush();
                    return true;
                })
                .orElse(false);
    }
}
