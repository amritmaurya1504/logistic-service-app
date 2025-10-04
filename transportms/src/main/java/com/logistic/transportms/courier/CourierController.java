package com.logistic.transportms.courier;

import com.logistic.transportms.courier.entity.Courier;
import com.logistic.transportms.courier.entity.CourierAssignment;
import com.logistic.transportms.courier.service.CourierService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/courier")
public class CourierController {

    @Autowired
    private CourierService courierService;

    // Create a new courier
    @PostMapping
    public ResponseEntity<Courier> createCourier(@RequestBody Courier courier) {
        Courier savedCourier = courierService.createCourier(courier);
        return ResponseEntity.ok(savedCourier);
    }

    @PutMapping("/shipment/{shipmentId}/status")
    public ResponseEntity<CourierAssignment> updateShipmentStatus(
            @PathVariable String shipmentId,
            @RequestParam String status) {

        CourierAssignment updatedAssignment = courierService.updateShipmentStatus(shipmentId, status);
        if (updatedAssignment == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(updatedAssignment);
    }

}
