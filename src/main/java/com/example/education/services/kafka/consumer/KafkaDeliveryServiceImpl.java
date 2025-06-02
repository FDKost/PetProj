package com.example.education.services.kafka.consumer;

import com.example.education.dto.order.OrderCreateEditDTO;
import com.example.education.dto.order.OrderReadDTO;
import com.example.education.services.order.OrderService;
import com.example.education.services.status.StatusService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class KafkaDeliveryServiceImpl implements KafkaDeliveryService {
    private final OrderService orderService;
    private final StatusService statusService;
    private final Logger LOGGER = LoggerFactory.getLogger(this.getClass());

    @Override
    public void handleMessage(String orderId, String pizzaList) {
        LOGGER.info("Received from delivery : {}", orderId);

        UUID uuid = UUID.fromString(orderId);

        var maybeOrder = orderService.findById(uuid);
        if (maybeOrder.isEmpty()) {
            LOGGER.warn("Order not found for id: {}", orderId);
            return; // или throw, если это критично
        }

        var maybeStatus = statusService.findByDescription("Waiting for courier");
        if (maybeStatus.isEmpty()) {
            LOGGER.warn("Status 'Waiting for courier' not found");
            return;
        }

        OrderReadDTO order = maybeOrder.get();
        OrderCreateEditDTO updatedOrder = new OrderCreateEditDTO();

        updatedOrder.setUserid(order.getUserid());
        updatedOrder.setStatus(maybeStatus.get());
        updatedOrder.setPaymentid(order.getPaymentid());
        updatedOrder.setDate(order.getDate());
        updatedOrder.setAddressid(order.getAddressid());

        orderService.update(uuid, updatedOrder);
    }

}
