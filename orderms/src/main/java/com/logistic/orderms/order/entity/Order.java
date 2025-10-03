package com.logistic.orderms.order.entity;

import com.logistic.orderms.order.entity.enums.OrderStatus;
import jakarta.persistence.*;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "\"order\"")
public class Order {
    @Id
    private String orderId;
    private String customerName;
    private String customerEmail;
    private String product;
    private int quantity;

    @Enumerated(EnumType.STRING)
    private OrderStatus status;

}
