package com.logistic.inventoryms.inventory.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class InventoryEventDTO {
    private String orderId;
    private String status;
    private String description;
}
