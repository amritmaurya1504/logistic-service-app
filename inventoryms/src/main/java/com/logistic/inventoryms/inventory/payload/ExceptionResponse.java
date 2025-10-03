package com.logistic.inventoryms.inventory.payload;

import lombok.*;
import org.springframework.http.HttpStatus;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class ExceptionResponse {
    private boolean success;
    private String message;
    private HttpStatus status;
}
