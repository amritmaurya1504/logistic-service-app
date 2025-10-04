package com.logistic.shippingms.shipment.service;

import com.logistic.shippingms.shipment.entity.Shipment;
import com.logistic.shippingms.shipment.entity.enums.ShipmentStatus;

public interface ShipmentService {
    Shipment createShipment(String orderId, String pickupAddress, String deliveryAddress);
    Shipment assignCourier(String shipmentId);
    Shipment updateShipmentStatus(String trackingId, ShipmentStatus status);
    Shipment getShipmentByOrderId(String string);
}
