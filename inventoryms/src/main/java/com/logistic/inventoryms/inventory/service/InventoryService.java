package com.logistic.inventoryms.inventory.service;


import com.logistic.inventoryms.inventory.entity.Product;
import com.logistic.inventoryms.inventory.entity.StockReservation;

import java.util.List;

public interface InventoryService {
    boolean checkStock(String productName, int quantity);
    StockReservation reserveStock(String orderId, String productName, int quantity);
    void releaseStock(String orderId);
    void commitStock(String orderId);
    List<Product> getProducts();
}
