package com.logistic.trackingms.tracking.dto;


import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

@JsonTypeInfo(
        use = JsonTypeInfo.Id.NAME,
        include = JsonTypeInfo.As.PROPERTY,
        property = "eventType"
)
@JsonSubTypes({
        @JsonSubTypes.Type(value = OrderEventDTO.class, name = "ORDER"),
        @JsonSubTypes.Type(value = InventoryEventDTO.class, name = "INVENTORY")
})
public interface EventDTO {
}
