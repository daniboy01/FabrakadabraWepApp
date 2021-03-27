package com.fabrakadabra.webapp.controller;

import com.fabrakadabra.webapp.service.ImageService;
import org.springframework.http.*;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
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
            imageService.saveImageToFileSys(imageFile,id);
        }catch (Exception e){
           e.printStackTrace();
           return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                   .body(imageFile.getOriginalFilename() + " upload error!");
        }
        return ResponseEntity.status(HttpStatus.OK)
                .body(imageFile.getOriginalFilename() + " uploaded succesfully!");
    }


}
