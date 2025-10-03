package com.logistic.trackingms.tracking.entity;


import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.*;

import java.time.LocalDateTime;


@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class TrackingLog {
    @Id
    private String trackingId;
    private String orderId;
    private String eventSource;
    private String status;
    private String description;
    private LocalDateTime createdAt;
}
