package com.fabrakadabra.webapp.controller;

import com.fabrakadabra.webapp.config.CrossOriginUrl;
import com.fabrakadabra.webapp.service.ImageService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class ImageController {
    private ImageService imageService;

    public ImageController(ImageService imageService) {
        this.imageService = imageService;
    }

//    @CrossOrigin(CrossOriginUrl.URl)
//    @PostMapping("/uploadImage/{id}")
//    public ResponseEntity<String> uploadImage(@RequestParam MultipartFile imageFile, @PathVariable Long id){
//        try {
//            imageService.saveImage(imageFile,id);
//        }catch (Exception e){
//           e.printStackTrace();
//           return ResponseEntity.status(HttpStatus.BAD_REQUEST)
//                   .body(imageFile.getOriginalFilename() + " upload error!");
//        }
//        return ResponseEntity.status(HttpStatus.OK)
//                .body(imageFile.getOriginalFilename() + " uploaded succesfully!");
//    }
//    @CrossOrigin(CrossOriginUrl.URl)
//    @PostMapping("/uploadImage/{id}")
//    public ResponseEntity<PlayGroundImgDTO> uploadImage(@RequestBody PlayGroundImgDTO dto, @PathVariable Long id){
//        return ResponseEntity.status(HttpStatus.OK)
//                .body(imageService.saveImage(dto,id));
//    }
}
