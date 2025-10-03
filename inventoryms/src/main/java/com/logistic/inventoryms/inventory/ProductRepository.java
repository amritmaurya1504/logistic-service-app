package com.logistic.inventoryms.inventory;

import com.logistic.inventoryms.inventory.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, String> {

}
