package com.logistic.shippingms.shipment;


import com.logistic.shippingms.shipment.entity.Shipment;
import com.logistic.shippingms.shipment.service.ShipmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/ship")
public class ShipmentController {

    @Autowired
    private ShipmentService shipmentService;

    @GetMapping("/{orderId}")
    public ResponseEntity<Shipment> getShipment(@PathVariable String orderId){
        return new ResponseEntity<>(shipmentService.getShipmentByOrderId(orderId), HttpStatus.OK);
    }

}
