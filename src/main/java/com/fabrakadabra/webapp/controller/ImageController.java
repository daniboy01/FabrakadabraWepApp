package com.fabrakadabra.webapp.controller;

import com.fabrakadabra.webapp.service.ImageService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@Controller
public class ImageController {
    private ImageService imageService;

    public ImageController(ImageService imageService) {
        this.imageService = imageService;
    }

    @PostMapping("/uploadImage/{id}")
    public ResponseEntity<String> uploadImage(@RequestParam MultipartFile imageFile, @PathVariable Long id){
        try {
            imageService.saveImage(imageFile,id);
        }catch (Exception e){
           e.printStackTrace();
           return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                   .body(imageFile.getOriginalFilename() + " upload error!");
        }
        return ResponseEntity.status(HttpStatus.OK)
                .body(imageFile.getOriginalFilename() + " uploaded succesfully!");
    }
}
