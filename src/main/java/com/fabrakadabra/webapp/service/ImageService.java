package com.fabrakadabra.webapp.service;

import com.fabrakadabra.webapp.dto.PlayGroundImgDTO;
import com.fabrakadabra.webapp.model.PlayGround;
import com.fabrakadabra.webapp.model.PlayGroundImg;
import com.fabrakadabra.webapp.repository.PlayGroundImgRepository;
import com.fabrakadabra.webapp.repository.PlayGroundRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Service
@AllArgsConstructor
public class ImageService  {
    private final String folder = "src/img/";
    private PlayGroundImgRepository playGroundImgRepository;
    private PlayGroundRepository playGroundRepository;

    public void saveImageToFileSys(MultipartFile imageFile,Long id) throws Exception {
        byte [] bytes = imageFile.getBytes();
        Path path = Paths.get(folder + imageFile.getOriginalFilename());
        Files.write(path,bytes);
        saveImageToPlayGroundImageFileSys(imageFile.getOriginalFilename(),id);
    }

    private void saveImageToPlayGroundImageFileSys(String imageName,Long id){
        File file = new File(folder + "/" + imageName);
        String absolutePath = file.getAbsolutePath();
        PlayGroundImg save = new PlayGroundImg();
        save.setURL(absolutePath);
        PlayGround playGround = playGroundRepository.findById(id).get();
        save.setPlayGround(playGround);
        playGround.getPlayGroundImgs().add(save);
        playGroundImgRepository.save(save);
        playGroundRepository.save(playGround);
    }

    public PlayGroundImgDTO saveImage(PlayGroundImgDTO dto,Long id){
        PlayGroundImg img = playGroundImgRepository.save(imgDtotoModell(dto,id));
        PlayGround playGround = playGroundRepository.findById(id).get();
        playGround.getPlayGroundImgs().add(img);
        playGroundRepository.save(playGround);
        dto.setID(img.getId());
        return dto;
    }

    private PlayGroundImg imgDtotoModell(PlayGroundImgDTO dto,Long playGroundId){
        return PlayGroundImg.builder()
                .id(dto.getID())
                .URL(dto.getURL())
                .playGround(playGroundRepository.findById(playGroundId).get())
                .build();
    }

}
