package com.logistic.inventoryms.inventory.entity;

import com.logistic.inventoryms.inventory.entity.enums.ProductStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "\"product\"")
public class Product {
    @Id
    @Column(unique = true, nullable = false)
    private String productName;  // Unique identifier

    private int quantityAvailable;

    @Enumerated(EnumType.STRING)
    private ProductStatus status; // AVAILABLE / OUT_OF_STOCK

}
