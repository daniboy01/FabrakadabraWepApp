package com.fabrakadabra.webapp.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PlayGroundDto {
    private Long id;
    private String name;
    private String pictureURL;
    private Integer price;
}
