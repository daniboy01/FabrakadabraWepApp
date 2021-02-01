package com.fabrakadabra.webapp.controller;

import com.fabrakadabra.webapp.dto.DimensionDTO;
import com.fabrakadabra.webapp.dto.PlayGroundDto;
import com.fabrakadabra.webapp.service.PlayGroundCustomizationService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/customization")
@AllArgsConstructor
public class PlayGroundCustomizationController {
    private PlayGroundCustomizationService playGroundCustomizationService;

    @PutMapping("/{id}/dimensions")
    public ResponseEntity<PlayGroundDto> AddDimensionsToPlayGround(@RequestBody DimensionDTO dto, @PathVariable Long id){
        return ResponseEntity.status(HttpStatus.OK)
                .body(playGroundCustomizationService.addDimensionsToPlayGround(dto,id));
    }
}
