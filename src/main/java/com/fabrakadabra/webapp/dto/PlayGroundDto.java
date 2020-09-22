package com.fabrakadabra.webapp.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


import java.util.List;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PlayGroundDto {
        private Long id;
        private String name;
        private List<PlayGroundImgDTO> playGroundImgs;
        private Integer price;

}
