package com.example.education.services.order;

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
public class OrderServiceImpl implements OrderService {
    private final OrderRepository orderRepository;
    private final OrderCreateEditMapper orderCreateEditMapper;
    private final OrderReadMapper orderReadMapper;

    @Override
    public Optional<OrderReadDTO> findById(UUID id){
        return orderRepository.findById(id).map(orderReadMapper::map);
    }

    public List<OrderReadDTO> getAllOrders(){
        return orderRepository.findAllBy().stream()
                .map(orderReadMapper::map)
                .toList();
    }

    @Override
    @Transactional
    public OrderReadDTO create(OrderCreateEditDTO orderDTO){
        return Optional.of(orderDTO)
                .map(orderCreateEditMapper::map)
                .map(orderRepository::save)
                .map(orderReadMapper::map)
                .orElseThrow();
    }

    @Override
    @Transactional
    public Optional<OrderReadDTO> update(UUID id, OrderCreateEditDTO orderDTO){
        return orderRepository.findById(id)
                .map(entity -> orderCreateEditMapper.map(orderDTO, entity))
                .map(orderRepository::saveAndFlush)
                .map(orderReadMapper::map);
    }

    @Override
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
