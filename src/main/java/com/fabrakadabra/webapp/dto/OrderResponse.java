package com.fabrakadabra.webapp.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@NoArgsConstructor
@Builder
public class OrderResponse {
    private String responseMessage;
    private Long orderID;

    public OrderResponse(String responseMessage, Long orderID) {
        this.responseMessage = responseMessage;
        this.orderID = orderID;
    }
}
