package com.logistic.inventoryms.inventory.service;

import com.logistic.inventoryms.inventory.ProductRepository;
import com.logistic.inventoryms.inventory.StockRepository;
import com.logistic.inventoryms.inventory.entity.Product;
import com.logistic.inventoryms.inventory.entity.StockReservation;
import com.logistic.inventoryms.inventory.entity.enums.StockStatus;
import com.logistic.inventoryms.inventory.exception.ProductNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
public class InventoryServiceImpl implements InventoryService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private StockRepository stockRepository;

    @Override
    public boolean checkStock(String productName, int quantity) {
        Product product = productRepository.findById(productName).orElseThrow(
                () -> new ProductNotFoundException(productName + " not found in inventory!")
        );

        return product.getQuantityAvailable() >= quantity;
    }

    @Override
    @Transactional
    public StockReservation reserveStock(String orderId, String productName, int quantity) {
        Product product = productRepository.findById(productName).orElseThrow(
                () -> new ProductNotFoundException(productName + " not found in inventory!")
        );

        product.setQuantityAvailable(product.getQuantityAvailable() - quantity);
        productRepository.save(product);

        String[] splittedId = UUID.randomUUID().toString().split("-");
        String _id = splittedId[0] + splittedId[splittedId.length - 1];

        StockReservation stockReservation = StockReservation.builder()
                .orderId(orderId)
                .id(_id)
                .productName(productName)
                .reservedQuantity(quantity)
                .status(StockStatus.RESERVED)
                .createdAt(LocalDateTime.now())
                .updatedAt(LocalDateTime.now())
                .build();

        stockRepository.save(stockReservation);

        return stockReservation;
    }

    @Override
    public void releaseStock(String orderId) {

    }

    @Override
    public void commitStock(String orderId) {

    }

    @Override
    public List<Product> getProducts() {
        return productRepository.findAll();
    }
}
