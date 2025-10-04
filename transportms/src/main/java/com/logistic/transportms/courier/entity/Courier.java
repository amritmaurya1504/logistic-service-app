package com.logistic.transportms.courier.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class Courier {

    @Id
    private String courierId;
    private String name;
    private String supportNumber;
    private String email;
}
