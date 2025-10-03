package com.logistic.orderms.order.kafka;

import com.logistic.orderms.order.dto.InventoryEventDTO;
import com.logistic.orderms.order.entity.enums.StockStatus;
import com.logistic.orderms.order.service.OrderService;
import jdk.swing.interop.SwingInterOpUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class OrderKafkaConsumer {

    @Autowired
    private OrderService orderService;

    private static final Logger log = LoggerFactory.getLogger(OrderKafkaConsumer.class);

    @KafkaListener(topics = "inventory-events", groupId = "order-group")
    public void handleConsumerInventoryEvents(Map<String, Object> inventoryEvent){

        String orderId = (String) inventoryEvent.get("orderId");
        String statusStr = (String) inventoryEvent.get("status");
        StockStatus status = StockStatus.valueOf(statusStr);

        log.info("Received inventory event for orderId {}: status={}", orderId, status);

        if(status.equals(StockStatus.RESERVED)){
            // Update order status to CONFIRM
            orderService.updateOrderStatus(orderId, status);
            log.info("Order {} confirmed.", orderId);

        }else if(status.equals(StockStatus.RELEASED)){
            // Update order status to FAIL/CANCELLED
            orderService.updateOrderStatus(orderId, status);
            log.info("Order {} could not be fulfilled.", orderId);
        }else {
            log.warn("Unknown stock status received: {}", status);
        }
    }

}
