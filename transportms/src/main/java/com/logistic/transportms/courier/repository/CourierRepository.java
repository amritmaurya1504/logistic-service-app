package com.logistic.transportms.courier.repository;

import com.logistic.transportms.courier.entity.Courier;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CourierRepository extends JpaRepository<Courier, String> {
}
