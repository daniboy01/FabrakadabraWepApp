package com.fabrakadabra.webapp.dto;

import com.fabrakadabra.webapp.model.Dimensions;
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
        private Integer price;
        private String descripton;
        private List<PlayGroundImgDTO> playGroundImgs;
        private Dimensions dimensions;
}
