package com.example.education.handler;

import com.example.education.services.kafka.consumer.KafkaDeliveryService;
import lombok.RequiredArgsConstructor;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class KafkaDeliveryHandler {
    private final KafkaDeliveryService kafkaDeliveryService;

    @KafkaListener(topics = "pizzeria-created-events-topic")
    public void processFromDelivery(ConsumerRecord<String, String> message) {
        kafkaDeliveryService.handleMessage(message.key(), message.value());
    }
}
