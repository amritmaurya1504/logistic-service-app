package com.logistic.orderms.order;

import com.logistic.orderms.order.dto.OrderEventDTO;
import com.logistic.orderms.order.entity.Order;
import com.logistic.orderms.order.entity.enums.OrderStatus;
import com.logistic.orderms.order.entity.enums.StockStatus;
import com.logistic.orderms.order.kafka.OrderKafkaProducer;
import com.logistic.orderms.order.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/orders")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @Autowired
    private OrderKafkaProducer kafkaProducer;

    @PostMapping
    public ResponseEntity<Order> createOrder(@RequestBody Order order) {
        Order newOrder = orderService.createOrder(order);

        // 3. Publish to Kafka

        Map<String, Object> kafkapayload = new HashMap<>();
        kafkapayload.put("orderId", order.getOrderId());
        kafkapayload.put("productName", order.getProduct());
        kafkapayload.put("quantity", order.getQuantity());
        kafkapayload.put("status", OrderStatus.CREATED.name());
        kafkapayload.put("description", "Order created successfully!");
        kafkapayload.put("eventSource", "ORDER");
        kafkaProducer.publishOrderCreated(kafkapayload);

        return new ResponseEntity<>(newOrder, HttpStatus.CREATED);
    }

    @GetMapping("/{orderId}")
    public ResponseEntity<Order> getOrder(@PathVariable String orderId){
        return new ResponseEntity<>(orderService.getOrder(orderId), HttpStatus.OK);
    }

    @PatchMapping("/{orderId}/status")
    public ResponseEntity<Order> updateOrderStatus(@PathVariable String orderId, @RequestParam
    StockStatus status){
        return new ResponseEntity<>(orderService.updateOrderStatus(orderId, status), HttpStatus
                .OK);
    }

}
