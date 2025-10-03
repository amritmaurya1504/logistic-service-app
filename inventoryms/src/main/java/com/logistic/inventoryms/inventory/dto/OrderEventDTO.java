package com.logistic.inventoryms.inventory.dto;

import com.logistic.inventoryms.inventory.entity.enums.OrderStatus;
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
