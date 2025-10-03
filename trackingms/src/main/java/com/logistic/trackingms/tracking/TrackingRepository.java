package com.logistic.trackingms.tracking;

import com.logistic.trackingms.tracking.entity.TrackingLog;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TrackingRepository extends JpaRepository<TrackingLog, String> {
    List<TrackingLog> findByOrderIdOrderByCreatedAtAsc(String orderId);
}
