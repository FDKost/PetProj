package com.example.education.services;

import com.example.education.dto.order.OrderCreateEditDTO;
import com.example.education.dto.order.OrderReadDTO;
import com.example.education.mapper.order.OrderCreateEditMapper;
import com.example.education.mapper.order.OrderReadMapper;
import com.example.education.repositories.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RequiredArgsConstructor
@Service
@Transactional(readOnly = true)
public class OrderService {
    private final OrderRepository orderRepository;
    private final OrderCreateEditMapper orderCreateEditMapper;
    private final OrderReadMapper orderReadMapper;

    public Optional<OrderReadDTO> findById(UUID id){
        return orderRepository.findById(id).map(orderReadMapper::map);
    }

    public List<OrderReadDTO> getAllOrders(){
        return orderRepository.getAllOrders().stream()
                .map(orderReadMapper::map)
                .toList();
    }

    @Transactional
    public OrderReadDTO create(OrderCreateEditDTO orderDTO){
        return Optional.of(orderDTO)
                .map(orderCreateEditMapper::map)
                .map(orderRepository::save)
                .map(orderReadMapper::map)
                .orElseThrow();
    }

    @Transactional
    public Optional<OrderReadDTO> update(UUID id, OrderCreateEditDTO orderDTO){
        return orderRepository.findById(id)
                .map(entity -> orderCreateEditMapper.map(orderDTO, entity))
                .map(orderRepository::saveAndFlush)
                .map(orderReadMapper::map);
    }

    @Transactional
    public boolean delete(UUID id){
        return orderRepository.findById(id)
                .map(entity -> {
                    orderRepository.delete(entity);
                    orderRepository.flush();
                    return true;
                })
                .orElse(false);
    }
}
