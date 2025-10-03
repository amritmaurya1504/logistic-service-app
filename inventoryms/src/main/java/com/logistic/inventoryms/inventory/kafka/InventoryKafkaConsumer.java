package com.logistic.inventoryms.inventory.kafka;

import com.logistic.inventoryms.inventory.ProductRepository;
import com.logistic.inventoryms.inventory.dto.OrderEventDTO;
import com.logistic.inventoryms.inventory.entity.StockReservation;
import com.logistic.inventoryms.inventory.entity.enums.StockStatus;
import com.logistic.inventoryms.inventory.service.InventoryService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class InventoryKafkaConsumer {
    Logger log = LoggerFactory.getLogger(InventoryKafkaConsumer.class);
    @Autowired
    private KafkaTemplate<String, Object> kafkaTemplate;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private InventoryService inventoryService;

    @Autowired
    private InventoryKafkaProducer inventoryKafkaProducer;

    @KafkaListener(topics = "order-creation-events", groupId = "inventory-group")
    public void consumeOrderCreatedEvent(Map<String, Object> orderEventMap){
        String orderId = (String) orderEventMap.get("orderId");
        String productName = (String) orderEventMap.get("productName");
        int quantity = (Integer) orderEventMap.get("quantity");
        log.info("Received OrderCreated event for orderId: {} ", orderId);

        boolean isStockAvailable = inventoryService.checkStock(productName,
                quantity);

        if(isStockAvailable){
            // Reserve Stock and Order Confirmed
            StockReservation stockReservation = inventoryService.reserveStock(orderId,
                    productName,
                    quantity);

            inventoryKafkaProducer.publishStockReservedEvent(orderId,
                    StockStatus.RESERVED.name());

        }else{
            // Order Canceled
            inventoryKafkaProducer.publishStockUnReservedEvent(orderId,
                    StockStatus.RELEASED.name());
        }

    }

}
