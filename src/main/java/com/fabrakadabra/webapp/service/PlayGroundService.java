package com.fabrakadabra.webapp.service;

import com.fabrakadabra.webapp.dto.DimensionDTO;
import com.fabrakadabra.webapp.dto.PlayGroundDto;
import com.fabrakadabra.webapp.dto.PlayGroundImgDTO;
import com.fabrakadabra.webapp.model.Dimensions;
import com.fabrakadabra.webapp.model.PlayGround;
import com.fabrakadabra.webapp.model.PlayGroundImg;
import com.fabrakadabra.webapp.repository.DimensionsRepository;
import com.fabrakadabra.webapp.repository.PlayGroundImgRepository;
import com.fabrakadabra.webapp.repository.PlayGroundRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class PlayGroundService {
    private final PlayGroundRepository playGroundRepository;
    private final PlayGroundImgRepository playGroundImgRepository;
    private final DimensionsRepository dimensionsRepository;

    @Transactional
    public List<PlayGroundDto> getAll(){
        return playGroundRepository.findAll()
                .stream()
                .map(this::mapToDto)
                .collect(Collectors.toList());
    }

    public PlayGroundDto mapToDto(PlayGround playGround) {
        if(playGround.getDimensions() == null){
            return PlayGroundDto.builder().name(playGround.getName())
                    .id(playGround.getId())
                    .playGroundImgs(mapToPlayGroundImgDto(playGround.getPlayGroundImgs()))
                    .price(playGround.getPrice())
                    .descripton(playGround.getDescripton())
                    .dimensions(null)
                    .build();
        }
        else {
            return PlayGroundDto.builder().name(playGround.getName())
                    .id(playGround.getId())
                    .playGroundImgs(mapToPlayGroundImgDto(playGround.getPlayGroundImgs()))
                    .price(playGround.getPrice())
                    .descripton(playGround.getDescripton())
                    .dimensions(mapDimensionsDto(playGround.getDimensions(),playGround))
                    .build();
        }
    }

    private DimensionDTO mapDimensionsDto(Dimensions dimensions,PlayGround playGround){
        if (dimensions.getID() == null) return null;
        else {
            return DimensionDTO.builder()
                    .ID(dimensions.getID())
                    .heightInMetre(dimensions.getHeightInMetre())
                    .weightInKg(dimensions.getWeightInKg())
                    .widthInMetre(dimensions.getWidthInMetre())
                    .material(dimensions.getMaterial())
                    .depthInMetre(dimensions.getDepthInMetre())
                    .playGroundID(playGround.getId())
                    .build();
        }

    }

    private List<PlayGroundImgDTO> mapToPlayGroundImgDto(List<PlayGroundImg> playGroundImgs){
        List<PlayGroundImgDTO> dtos = new ArrayList<>();
        for (PlayGroundImg playGroundImg : playGroundImgs) {
            dtos.add(PlayGroundImgDTO.builder()
            .ID(playGroundImg.getId())
            .URL(playGroundImg.getURL())
            .build());
        }
        return dtos;
    }

    private List<PlayGroundImg> mapToPlaygroundImg(List<PlayGroundImgDTO> playGroundImgDTOS){
        List<PlayGroundImg> playGroundImgs = new ArrayList<>();
        for(PlayGroundImgDTO dto : playGroundImgDTOS){
            playGroundImgs.add(PlayGroundImg.builder()
            .id(dto.getID())
            .URL(dto.getURL())
            .build());
        }
        return playGroundImgs;
    }

    @Transactional
    public PlayGroundDto save(PlayGroundDto playGroundDto){
        PlayGround save = playGroundRepository.save(mapPlaygroundDto(playGroundDto));
        playGroundDto.setId(save.getId());
        List<PlayGroundImg> imgs = mapToPlaygroundImg(playGroundDto.getPlayGroundImgs());
        for(PlayGroundImg img : imgs){
            PlayGroundImg imgToSave = playGroundImgRepository.save(img);
            imgToSave.setPlayGround(save);
            playGroundImgRepository.save(imgToSave);
            for(PlayGroundImgDTO dto : playGroundDto.getPlayGroundImgs()){
                dto.setID(imgToSave.getId());
            }
        }
        save.setPlayGroundImgs(imgs);
        playGroundRepository.save(save);
        return playGroundDto;
    }

    private PlayGround mapPlaygroundDto(PlayGroundDto playGroundDto) {
        return PlayGround.builder()
                .id(playGroundDto.getId())
                .name(playGroundDto.getName())
                .playGroundImgs(mapToPlaygroundImg(playGroundDto.getPlayGroundImgs()))
                .price(playGroundDto.getPrice())
                .createdAt(Instant.now())
                .build();
    }


    @Transactional
    public PlayGroundDto getPlaygroundById(Long id) {
        return mapToDto(playGroundRepository.findById(id).get());
    }

    @Transactional
    public PlayGroundDto editPlayground(PlayGroundDto playGroundDto) {
        Long id = playGroundDto.getId();
        PlayGround playGround = playGroundRepository.findById(id).get();
        playGround.setName(playGroundDto.getName());
        playGround.setPlayGroundImgs(mapToPlaygroundImg(playGroundDto.getPlayGroundImgs()));
        playGround.setPrice(playGroundDto.getPrice());
        return mapToDto(playGroundRepository.save(playGround));
    }

    @Transactional
    public void delete(Long id) {
        playGroundRepository.deleteById(id);
    }
}
