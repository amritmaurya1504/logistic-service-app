package com.logistic.shippingms.shipment.service;

import com.logistic.shippingms.shipment.ShipmentRepository;
import com.logistic.shippingms.shipment.entity.Shipment;
import com.logistic.shippingms.shipment.entity.enums.ShipmentStatus;
import com.logistic.shippingms.shipment.kafka.ShipmentKafkaProducer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
public class ShipmentServiceImpl implements ShipmentService{

    private static final Logger log = LoggerFactory.getLogger(ShipmentServiceImpl.class);
    @Autowired
    private ShipmentRepository shipmentRepository;

    @Autowired
    private ShipmentKafkaProducer shipmentKafkaProducer;

    @Override
    public Shipment createShipment(String orderId, String pickupAddress, String deliveryAddress) {

        String shipmentId = "SHIP-" + UUID.randomUUID().toString().substring(0,8);
        Shipment shipment = Shipment.builder()
                .shipmentId(shipmentId)
                .orderId(orderId)
                .pickupAddress(pickupAddress)
                .deliveryAddress(deliveryAddress)
                .status(ShipmentStatus.CREATED)
                .createdAt(LocalDateTime.now())
                .updatedAt(LocalDateTime.now())
                .build();

        Shipment newShipment = shipmentRepository.save(shipment);

        shipmentKafkaProducer.publishShipmentCreatedEvent(newShipment);
        log.info("Shipment created for order: {} ", orderId);

        return newShipment;
    }

    @Override
    public Shipment assignCourier(String shipmentId) {
        return null;
    }

    @Override
    public Shipment updateShipmentStatus(String trackingId, ShipmentStatus status) {
        return null;
    }

    @Override
    public Shipment getShipmentByOrderId(String orderId) {
        return shipmentRepository.findByOrderId(orderId);
    }
}
