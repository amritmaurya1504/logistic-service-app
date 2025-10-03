package com.logistic.trackingms.tracking.dto;

import com.logistic.trackingms.tracking.entity.enums.StockStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class InventoryEventDTO implements EventDTO{
    private String orderId;
    private StockStatus status;
    private String description;
}
