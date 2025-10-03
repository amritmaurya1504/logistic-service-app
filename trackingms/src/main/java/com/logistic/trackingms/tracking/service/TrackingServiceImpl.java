package com.logistic.trackingms.tracking.service;

import com.logistic.trackingms.tracking.TrackingRepository;
import com.logistic.trackingms.tracking.entity.TrackingLog;
import com.logistic.trackingms.tracking.entity.enums.TrackedService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
public class TrackingServiceImpl implements TrackingService{

    @Autowired
    private TrackingRepository trackingRepository;

    @Override
    public List<TrackingLog> findTrackerLogById(String orderId) {
        return trackingRepository.findByOrderIdOrderByCreatedAtAsc(orderId);
    }

    @Override
    public void saveTrackingLog(String orderId, String status, String trackedService,
                                String description) {
        String[] splittedId = UUID.randomUUID().toString().split("-");
        String _id = splittedId[0] + splittedId[splittedId.length - 1];

        TrackingLog log = TrackingLog.builder()
                .trackingId(_id)
                .orderId(orderId)
                .status(status)
                .eventSource(trackedService)
                .createdAt(LocalDateTime.now())
                .description(description)
                .build();

        trackingRepository.save(log);
    }
}
