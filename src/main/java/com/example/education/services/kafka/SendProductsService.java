package com.example.education.services.kafka;

import java.util.UUID;

public interface SendProductsService {
    String sendProducts(UUID cartId);
}
