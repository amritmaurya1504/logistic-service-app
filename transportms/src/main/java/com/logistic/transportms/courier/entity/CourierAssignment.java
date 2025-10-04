package com.logistic.transportms.courier.entity;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CourierAssignment {

    @Id
    private String id;

    private String shipmentId;
    private String orderId;
    private String courierId;
    private LocalDateTime assignedAt;
    private String status; // ASSIGNED, ACCEPTED, PICKED_UP, IN_TRANSIT, DELIVERED
    private LocalDateTime updatedAt;
}
