package com.logistic.inventoryms.inventory;

import com.logistic.inventoryms.inventory.entity.StockReservation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StockRepository extends JpaRepository<StockReservation, String> {
}
