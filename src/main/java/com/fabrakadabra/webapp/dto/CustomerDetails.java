package com.fabrakadabra.webapp.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CustomerDetails {
    private String firstName;
    private String lastName;
    private String email;
    private String address;
    private String phoneNumber;
}
