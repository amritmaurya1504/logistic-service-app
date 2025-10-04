package com.logistic.trackingms.tracking.kafka;

import com.logistic.trackingms.tracking.TrackingRepository;
import com.logistic.trackingms.tracking.dto.EventDTO;
import com.logistic.trackingms.tracking.dto.InventoryEventDTO;
import com.logistic.trackingms.tracking.dto.OrderEventDTO;
import com.logistic.trackingms.tracking.entity.TrackingLog;
import com.logistic.trackingms.tracking.entity.enums.TrackedService;
import com.logistic.trackingms.tracking.service.TrackingService;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Map;
import java.util.UUID;

@Service
public class TrackingKafkaConsumer {

    @Autowired
    private TrackingService trackingService;

    @KafkaListener(topics = {"order-creation-events", "order-status-events", "inventory-events",
    "shipment-created-event"},
            groupId = "tracking-group")
    public void handleConsumeEvent(Map<String, Object> eventPayload){
        // Extract common fields
        String orderId = (String) eventPayload.get("orderId");
        String description = (String) eventPayload.get("description");
        String eventSource = (String) eventPayload.get("eventSource"); // add eventType when publishing
        String status = (String) eventPayload.get("status");

        // Save log
        trackingService.saveTrackingLog(orderId, status, eventSource, description);
    }

}
