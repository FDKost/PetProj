package com.example.education.services.kafka.producer;

import java.util.UUID;

public interface SendProductsService {
    String sendProducts(UUID orderId,UUID cartId);
}
