package com.fabrakadabra.webapp.controller;

import com.fabrakadabra.webapp.service.ImageService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import org.springframework.web.multipart.MultipartFile;

@Controller
public class ImageController {
    private ImageService imageService;

    public ImageController(ImageService imageService) {
        this.imageService = imageService;
    }

    @PostMapping("/uploadImage/{id}")
    public String uploadImage(@RequestBody MultipartFile imageFile, @PathVariable Long id){
        String returnValue = "";
        try {
            imageService.saveImage(imageFile,id);
        }catch (Exception e){
           return "Image upload error";
        }
        return returnValue;
    }
}
