package com.logistic.trackingms.tracking.service;

import com.logistic.trackingms.tracking.entity.TrackingLog;
import com.logistic.trackingms.tracking.entity.enums.TrackedService;

import java.util.List;

public interface TrackingService {

    List<TrackingLog> findTrackerLogById(String orderId);
    void saveTrackingLog(String orderId, String status, String trackedService, String description);
}
