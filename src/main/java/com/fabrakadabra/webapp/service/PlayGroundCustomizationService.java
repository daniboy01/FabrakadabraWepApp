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
    private PlayGroundService playGroundService;

    public PlayGroundDto addDimensionsToPlayGround(DimensionDTO dto, Long id) {
        PlayGround playGround = playGroundRepository.findById(id).get();
        Dimensions dimensions = dimensionsRepository.save(mapDtoToModel(dto,playGround));
        dto.setPlayGroundID(id);
        playGround.setDimensions(dimensions);
        dimensions.setPlayGround(playGround);
        playGroundRepository.save(playGround);
        dimensionsRepository.save(dimensions);
        return playGroundService.mapToDto(playGround);
    }

    private Dimensions mapDtoToModel(DimensionDTO dto,PlayGround playGround){
        return Dimensions.builder()
                .ID(dto.getID())
                .heightInMetre(dto.getHeightInMetre())
                .weightInKg(dto.getWeightInKg())
                .widthInMetre(dto.getWidthInMetre())
                .material(dto.getMaterial())
                .depthInMetre(dto.getDepthInMetre())
                .playGround(playGround)
                .build();
    }
}
