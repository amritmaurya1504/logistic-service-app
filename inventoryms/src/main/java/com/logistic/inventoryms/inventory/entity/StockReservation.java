package com.logistic.inventoryms.inventory.entity;

import com.logistic.inventoryms.inventory.entity.enums.StockStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Builder
@Table(name = "\"stock_reservation\"")
public class StockReservation {
    @Id
    private String id;

    private String orderId;

    private String productName;  // links to Item.productName

    private int reservedQuantity;

    @Enumerated(EnumType.STRING)
    private StockStatus status; // RESERVED / COMMITTED / RELEASED

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
