package com.logistic.transportms.courier.kafka;

import com.logistic.transportms.courier.entity.CourierAssignment;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@Service
public class CourierKafkaProducer {

    @Autowired
    private KafkaTemplate<String, Map<String, Object>> kafkaTemplate;
    private final String COURIER_ASSIGNED_TOPIC = "courier-assigned-topic";
    private final String SHIPMENT_STATUS_UPDATED_TOPIC = "courier-shipment-status-topic";

    public void publishEventCourierAssigned(CourierAssignment courierAssignment){
        Map<String, Object> event = new HashMap<>();
        event.put("shipmentId", courierAssignment.getShipmentId());
        event.put("orderId", courierAssignment.getOrderId());
        event.put("status", courierAssignment.getStatus());
        event.put("eventSource", "COURIER");
        event.put("description", "Courier assigned for order: " + courierAssignment.getOrderId());

        kafkaTemplate.send(COURIER_ASSIGNED_TOPIC, event);
        log.info("Courier assigned event published for shipmentId: {} ",courierAssignment.getShipmentId());
    }

    public void publishEventShipmentStatusUpdated(CourierAssignment courierAssignment){
        Map<String, Object> event = new HashMap<>();
        event.put("shipmentId", courierAssignment.getShipmentId());
        event.put("orderId", courierAssignment.getOrderId());
        event.put("status", courierAssignment.getStatus());
        event.put("eventSource", "COURIER");
        event.put("description", "Courier assigned for order: " + courierAssignment.getOrderId());

        kafkaTemplate.send(SHIPMENT_STATUS_UPDATED_TOPIC, event);
        log.info("Courier assigned event published for shipmentId: {} ",courierAssignment.getShipmentId());
    }

}
