package com.fabrakadabra.webapp.service;

import com.fabrakadabra.webapp.model.PlayGroundImg;
import com.fabrakadabra.webapp.repository.PlayGroundImgRepository;
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

    public void saveImage(MultipartFile imageFile,Long id) throws Exception {
        byte [] bytes = imageFile.getBytes();
        Path path = Paths.get(folder + imageFile.getOriginalFilename());
        Files.write(path,bytes);
        imageToPlayGroundImage(imageFile.getOriginalFilename(),id);
    }

    public void imageToPlayGroundImage(String imageName,Long id){
        File file = new File(folder + "/" + imageName);
        String absolutePath = file.getAbsolutePath();
        PlayGroundImg save = playGroundImgRepository.findById(id).get();
        save.setURL(absolutePath);
        playGroundImgRepository.save(save);
    }
}
