package com.logistic.transportms.courier.service;

import com.logistic.transportms.courier.entity.Courier;
import com.logistic.transportms.courier.entity.CourierAssignment;
import com.logistic.transportms.courier.kafka.CourierKafkaProducer;
import com.logistic.transportms.courier.repository.CourierAssignmentRepository;
import com.logistic.transportms.courier.repository.CourierRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

@Service
public class CourierServiceImpl implements CourierService{

    @Autowired
    private CourierRepository courierRepository;

    @Autowired
    private CourierAssignmentRepository courierAssignmentRepository;

    @Autowired
    private CourierKafkaProducer courierKafkaProducer;

    @Override
    public Courier createCourier(Courier courier) {
        return courierRepository.save(courier);
    }

    @Override
    public Optional<Courier> getCourier(String courierId) {
        return courierRepository.findById(courierId);
    }

    @Override
    public CourierAssignment createCourierAssignment(String shipmentId, String orderId) {
        CourierAssignment courierAssignment = CourierAssignment.builder()
                .id(UUID.randomUUID().toString())
                .courierId("DEL123")
                .assignedAt(LocalDateTime.now())
                .shipmentId(shipmentId)
                .orderId(orderId)
                .status("COURIER_ASSIGNED")
                .updatedAt(LocalDateTime.now())
                .build();

        return courierAssignmentRepository.save(courierAssignment);
    }

    @Override
    public CourierAssignment updateShipmentStatus(String shipmentId, String status) {

        CourierAssignment courierAssignment = courierAssignmentRepository.findByShipmentId(
                shipmentId
        );

        courierAssignment.setStatus(status);

        CourierAssignment updatedCourierAssignment = courierAssignmentRepository.save(courierAssignment);
        // Publish Event;
        courierKafkaProducer.publishEventShipmentStatusUpdated(updatedCourierAssignment);

        return updatedCourierAssignment;
    }
}
