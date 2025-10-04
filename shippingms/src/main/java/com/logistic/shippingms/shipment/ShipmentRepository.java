package com.logistic.shippingms.shipment;

import com.logistic.shippingms.shipment.entity.Shipment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ShipmentRepository extends JpaRepository<Shipment, String> {
    Shipment findByOrderId(String orderId);
}
