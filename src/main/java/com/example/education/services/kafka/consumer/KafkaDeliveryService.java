package com.example.education.services.kafka.consumer;

public interface KafkaDeliveryService {
    void handleMessage(String orderId,String pizzaList);
}
