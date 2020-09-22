package com.fabrakadabra.webapp.service;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Service
public class ImageService  {

    public void saveImage(MultipartFile imageFile) throws Exception {
        String folder = "src/img/";
        byte [] bytes = imageFile.getBytes();
        Path path = Paths.get(folder + imageFile.getOriginalFilename());
        Files.write(path,bytes);
    }
}
