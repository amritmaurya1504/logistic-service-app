package com.logistic.trackingms.tracking;

import com.logistic.trackingms.tracking.entity.TrackingLog;
import com.logistic.trackingms.tracking.service.TrackingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/track")
public class TrackingController {

    @Autowired
    private TrackingService trackingService;

    @GetMapping("/{orderId}")
    public ResponseEntity<List<TrackingLog>> getTrackingLog(@PathVariable String orderId){
        return new ResponseEntity<>(trackingService.findTrackerLogById(orderId), HttpStatus.OK);
    }

}
