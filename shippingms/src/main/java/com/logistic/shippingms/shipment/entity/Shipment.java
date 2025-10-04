package com.logistic.shippingms.shipment.entity;

import com.logistic.shippingms.shipment.entity.enums.ShipmentStatus;
import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "shipments")
public class Shipment {

    @Id
    private String shipmentId;

    private String orderId;
    private String courier;       // e.g., "Delhivery"
    private String trackingId;    // courier tracking number
    private String pickupAddress;
    private String deliveryAddress;

    @Enumerated(EnumType.STRING)
    private ShipmentStatus status;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private LocalDateTime deliveredAt;
}
