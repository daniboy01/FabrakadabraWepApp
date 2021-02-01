package com.fabrakadabra.webapp.service;

import com.fabrakadabra.webapp.dto.DimensionDTO;
import com.fabrakadabra.webapp.dto.PlayGroundDto;
import com.fabrakadabra.webapp.model.Dimensions;
import com.fabrakadabra.webapp.model.PlayGround;
import com.fabrakadabra.webapp.repository.DimensionsRepository;
import com.fabrakadabra.webapp.repository.PlayGroundRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class PlayGroundCustomizationService {
    private DimensionsRepository dimensionsRepository;
    private PlayGroundRepository playGroundRepository;

    public PlayGroundDto addDimensionsToPlayGround(DimensionDTO dto, Long id) {
        return null;
    }
}
