package com.logistic.shippingms.shipment.kafka;

import com.logistic.shippingms.shipment.entity.Shipment;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class ShipmentKafkaProducer {

    private static final Logger log = LoggerFactory.getLogger(ShipmentKafkaProducer.class);
    @Autowired
    private KafkaTemplate<String, Map<String, Object>> kafkaTemplate;
    private final String SHIPMENT_CREATED_TOPIC = "shipment-created-event";

    public void publishShipmentCreatedEvent(Shipment shipment){
        Map<String, Object> event = new HashMap<>();
        event.put("shipmentId", shipment.getShipmentId());
        event.put("orderId", shipment.getOrderId());
        event.put("status", shipment.getStatus());
        event.put("eventSource", "SHIPMENT");
        event.put("description", "Shipment created for order: " + shipment.getOrderId());

        kafkaTemplate.send(SHIPMENT_CREATED_TOPIC, event);
        log.info("Shipment creation event published!");
    }

}
