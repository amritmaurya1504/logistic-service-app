package com.logistic.orderms.order.kafka;

import com.logistic.orderms.order.dto.OrderEventDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class OrderKafkaProducer {
    Logger log = LoggerFactory.getLogger(OrderKafkaProducer.class);
    private final KafkaTemplate<String, Map<String, Object>> kafkaTemplate;
    private final String ORDER_CREATED_TOPIC = "order-creation-events";
    private final String ORDER_STATUS_TOPIC = "order-status-events";

    public OrderKafkaProducer(KafkaTemplate<String, Map<String, Object>> kafkaTemplate){
        this.kafkaTemplate = kafkaTemplate;
    }

    public void publishOrderCreated(Map<String, Object> kafkapayload){
        kafkaTemplate.send(ORDER_CREATED_TOPIC, kafkapayload);
        log.info("OrderCreated event published for orderId: {}", kafkapayload.get("orderId"));
    }

    public void publishOrderStatusChanged(Map<String, Object> kafkaPayload){
        kafkaTemplate.send(ORDER_STATUS_TOPIC, kafkaPayload);
        log.info("Order status changed for orderId: {}", kafkaPayload.get("status"));
    }
}
