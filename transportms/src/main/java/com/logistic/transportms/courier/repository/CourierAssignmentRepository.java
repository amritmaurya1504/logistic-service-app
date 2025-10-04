package com.logistic.transportms.courier.repository;

import com.logistic.transportms.courier.entity.CourierAssignment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CourierAssignmentRepository extends JpaRepository<CourierAssignment, String> {
    CourierAssignment findByShipmentId(String shipmentId);
}
