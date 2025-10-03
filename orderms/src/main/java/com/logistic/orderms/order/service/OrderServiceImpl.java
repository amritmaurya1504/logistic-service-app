package com.logistic.orderms.order.service;

import com.logistic.orderms.order.OrderRepository;
import com.logistic.orderms.order.dto.OrderEventDTO;
import com.logistic.orderms.order.entity.Order;
import com.logistic.orderms.order.entity.enums.OrderStatus;
import com.logistic.orderms.order.entity.enums.StockStatus;
import com.logistic.orderms.order.exception.OrderNotFoundException;
import com.logistic.orderms.order.kafka.OrderKafkaProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.UUID;

@Service
public class OrderServiceImpl implements OrderService{

    /* Constructor injection is recommended */
    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private OrderKafkaProducer orderKafkaProducer;


    @Override
    public Order createOrder(Order order) {
        String[] splittedId = UUID.randomUUID().toString().split("-");
        String _id = splittedId[0] + splittedId[splittedId.length - 1];
        order.setOrderId(_id);
        order.setStatus(OrderStatus.CREATED);
        return orderRepository.save(order);
    }

    @Override
    public Order getOrder(String orderId) {
        Order order = orderRepository.findById(orderId).orElseThrow(() ->
                new OrderNotFoundException("Order with id: " + orderId + " not exist!"));

        return order;
    }

    @Override
    public Order updateOrderStatus(String orderId, StockStatus status) {
        Order order = orderRepository.findById(orderId).orElseThrow(() ->
                new OrderNotFoundException("Order with id: " + orderId + " not exist!"));

        Map<String, Object> kafkaPayload = new HashMap<>();
        kafkaPayload.put("orderId", orderId);
        kafkaPayload.put("productName", order.getProduct());
        kafkaPayload.put("quantity", order.getQuantity());
        kafkaPayload.put("description", "Order with id: " + orderId + " is " + status);
        kafkaPayload.put("eventSource", "ORDER");

        if(status.equals(StockStatus.RESERVED)){
            order.setStatus(OrderStatus.CONFIRMED);
            kafkaPayload.put("status", OrderStatus.CONFIRMED.name());
            orderKafkaProducer.publishOrderStatusChanged(kafkaPayload);
        }else if(status.equals(StockStatus.RELEASED)){
            order.setStatus(OrderStatus.FAILED);
            kafkaPayload.put("status", OrderStatus.FAILED.name());
            orderKafkaProducer.publishOrderStatusChanged(kafkaPayload);
        }
        return orderRepository.save(order);
    }
}
