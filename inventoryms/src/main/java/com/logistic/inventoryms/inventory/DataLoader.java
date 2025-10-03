package com.logistic.inventoryms.inventory;

import com.logistic.inventoryms.inventory.entity.Product;
import com.logistic.inventoryms.inventory.entity.enums.ProductStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataLoader implements CommandLineRunner {

    @Autowired
    private ProductRepository productRepository;


    @Override
    public void run(String... args) throws Exception {
        if (productRepository.count() == 0) { // only if empty
            Product p1 = new Product("Iphone 17", 50, ProductStatus.AVAILABLE);
            Product p2 = new Product("MacBook Air", 30, ProductStatus.AVAILABLE);
            Product p3 = new Product("Acer Aspire 7", 20, ProductStatus.AVAILABLE);

            productRepository.save(p1);
            productRepository.save(p2);
            productRepository.save(p3);

            System.out.println("Dummy products loaded.");
        }
    }
}
