package com.logistic.inventoryms.inventory.kafka;

import com.logistic.inventoryms.inventory.dto.InventoryEventDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class InventoryKafkaProducer {
    Logger log = LoggerFactory.getLogger(InventoryKafkaProducer.class);
    @Autowired
    private KafkaTemplate<String, Map<String, Object>> kafkaTemplate;
    private final String INVENTORY_TOPIC = "inventory-events";

    public void publishStockReservedEvent(String orderId, String status){

        Map<String, Object> payload = new HashMap<>();
        payload.put("orderId", orderId);
        payload.put("status", status);
        payload.put("description", "Stock reserved successfully!");
        payload.put("eventSource", "INVENTORY");

        kafkaTemplate.send(INVENTORY_TOPIC, payload);
        log.info("Stock Reserved for orderId: {}", orderId);
    }

    public void publishStockUnReservedEvent(String orderId, String status){
        Map<String, Object> payload = new HashMap<>();
        payload.put("orderId", orderId);
        payload.put("status", status);
        payload.put("description", "Item out of stock!");
        payload.put("eventSource", "INVENTORY");

        kafkaTemplate.send(INVENTORY_TOPIC, payload);
        log.info("Stock UnReserved published for orderId: {}", orderId);
    }

}
