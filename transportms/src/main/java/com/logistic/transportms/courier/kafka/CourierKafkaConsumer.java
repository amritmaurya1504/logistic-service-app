package com.logistic.transportms.courier.kafka;

import com.logistic.transportms.courier.entity.CourierAssignment;
import com.logistic.transportms.courier.service.CourierService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.util.Map;

@Slf4j
@Service
public class CourierKafkaConsumer {

    @Autowired
    private CourierService courierService;

    @Autowired
    private CourierKafkaProducer courierKafkaProducer;

    @KafkaListener(topics = "shipment-created-event", groupId = "courier-group")
    public void handleConsumeShipmentCreatedEvent(Map<String, Object> event){
        String shipmentId = (String) event.get("shipmentId");
        String status = (String) event.get("status");
        String orderId = (String) event.get("orderId");

        if(status.equalsIgnoreCase("CREATED")){
            CourierAssignment assignment = courierService.createCourierAssignment(shipmentId, orderId);
            courierKafkaProducer.publishEventCourierAssigned(assignment);
            log.info("Courier assigned for shipmentId: {}", shipmentId);
        }
    }

}
