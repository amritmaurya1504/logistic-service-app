package com.logistic.transportms.courier.service;

import com.logistic.transportms.courier.entity.Courier;
import com.logistic.transportms.courier.entity.CourierAssignment;

import java.util.Optional;

public interface CourierService {

    Courier createCourier(Courier courier);
    Optional<Courier> getCourier(String courierId);
    CourierAssignment createCourierAssignment(String shipmentId, String orderId);
    CourierAssignment updateShipmentStatus(String shipmentId, String status);

}
