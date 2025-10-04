package com.logistic.shippingms.shipment.kafka;

import com.logistic.shippingms.shipment.entity.Shipment;
import com.logistic.shippingms.shipment.service.ShipmentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class ShipmentKafkaConsumer {

    private static final Logger log = LoggerFactory.getLogger(ShipmentKafkaConsumer.class);
    @Autowired
    private ShipmentService shipmentService;

    @KafkaListener(topics = "order-status-events", groupId = "shipment-group")
    public void handleConsumeOrderConfirmedEvent(Map<String, Object> orderConfirmedEvent){
        // Extract common fields
        String orderId = (String) orderConfirmedEvent.get("orderId");
        String description = (String) orderConfirmedEvent.get("description");
        String eventSource = (String) orderConfirmedEvent.get("eventSource"); // add eventType when publishing
        String status = (String) orderConfirmedEvent.get("status");

        if(status.equalsIgnoreCase("CONFIRMED")){
            /* Create Shipment */
            shipmentService.createShipment(
                    orderId, "Warehouse 1", "Siya Saran Niwas, Jehanabad"
            );
        }
    }

}
