package com.logistic.orderms.order.service;

import com.logistic.orderms.order.entity.Order;
import com.logistic.orderms.order.entity.enums.StockStatus;

public interface OrderService {
    Order createOrder(Order order);
    Order getOrder(String orderId);
    Order updateOrderStatus(String orderId, StockStatus status);
}
