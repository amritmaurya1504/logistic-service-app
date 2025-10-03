package com.logistic.trackingms.tracking.dto;

import com.logistic.trackingms.tracking.entity.enums.OrderStatus;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OrderEventDTO implements EventDTO{
    private String orderId;
    private String productName;
    private int quantity;
    private OrderStatus status;
    private String description;
}
