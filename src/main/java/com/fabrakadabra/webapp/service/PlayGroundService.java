package com.fabrakadabra.webapp.service;

import com.fabrakadabra.webapp.dto.PlayGroundDto;
import com.fabrakadabra.webapp.model.PlayGround;
import com.fabrakadabra.webapp.repository.PlayGroundRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.Instant;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class PlayGroundService {
    private final PlayGroundRepository playGroundRepository;

    @Transactional
    public List<PlayGroundDto> getAll(){
        return playGroundRepository.findAll()
                .stream()
                .map(this::mapToDto)
                .collect(Collectors.toList());
    }

    private PlayGroundDto mapToDto(PlayGround playGround) {
        return PlayGroundDto.builder().name(playGround.getName())
                .id(playGround.getId())
                .pictureURL(playGround.getPictureURL())
                .price(playGround.getPrice())
                .build();
    }

    @Transactional
    public PlayGroundDto save(PlayGroundDto playGroundDto){
        PlayGround save = playGroundRepository.save(mapPlaygroundDto(playGroundDto));
        playGroundDto.setId(save.getId());
        return playGroundDto;
    }

    private PlayGround mapPlaygroundDto(PlayGroundDto playGroundDto) {
        return PlayGround.builder()
                .id(playGroundDto.getId())
                .name(playGroundDto.getName())
                .pictureURL(playGroundDto.getPictureURL())
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
        playGround.setPictureURL(playGroundDto.getPictureURL());
        playGround.setPrice(playGroundDto.getPrice());
        return mapToDto(playGroundRepository.save(playGround));
    }

    @Transactional
    public void delete(Long id) {
        playGroundRepository.deleteById(id);
    }
}
