package com.fabrakadabra.webapp.controller;

import com.fabrakadabra.webapp.service.ImageService;
import org.springframework.stereotype.Controller;
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

    @PostMapping("/uploadImage")
    public String uploadImage(@RequestBody MultipartFile imageFile){
        String returnValue = "";
        try {
            imageService.saveImage(imageFile);
        }catch (Exception e){
           return "Image upload error";
        }
        return returnValue;
    }
}
