package com.logistic.orderms.order.dto;

import com.logistic.orderms.order.entity.enums.OrderStatus;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OrderEventDTO {
    private String orderId;
    private String productName;
    private int quantity;
    private OrderStatus status;
    private String description;
}
