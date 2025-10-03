package com.logistic.orderms.order.dto;

import com.logistic.orderms.order.entity.enums.StockStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class InventoryEventDTO {
    private String orderId;
    private StockStatus status;
    private String description;
}
