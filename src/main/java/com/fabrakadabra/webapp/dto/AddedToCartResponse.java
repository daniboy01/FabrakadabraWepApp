package com.fabrakadabra.webapp.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AddedToCartResponse {
    private UUID id;
    private String responseMessage;

    public AddedToCartResponse(String message){
        id = UUID.randomUUID();
        responseMessage = message;
    }
}
