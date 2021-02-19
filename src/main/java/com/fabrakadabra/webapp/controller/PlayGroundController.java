package com.fabrakadabra.webapp.controller;

import com.fabrakadabra.webapp.config.CrossOriginUrl;
import com.fabrakadabra.webapp.dto.PlayGroundDto;
import com.fabrakadabra.webapp.service.PlayGroundService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/playground")
@AllArgsConstructor
public class PlayGroundController {
    private final PlayGroundService playGroundService;

    @CrossOrigin(CrossOriginUrl.URl)
    @GetMapping("/getAll")
    public ResponseEntity<List<PlayGroundDto>> getAll(){
        System.out.println("meghívta");
        return  ResponseEntity
                .status(HttpStatus.OK)
                .body(playGroundService.getAll());
    }

    @CrossOrigin(CrossOriginUrl.URl)
    @PostMapping("/createPlayground")
    public ResponseEntity<PlayGroundDto> createPlayground(@RequestBody PlayGroundDto playGroundDto){
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(playGroundService.save(playGroundDto));
    }

    @CrossOrigin(CrossOriginUrl.URl)
    @GetMapping("/{id}")
    public ResponseEntity<PlayGroundDto> getPlayground(@PathVariable Long id){
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(playGroundService.getPlaygroundById(id));
    }

    @CrossOrigin(CrossOriginUrl.URl)
    @PostMapping("/edit")
    public ResponseEntity<PlayGroundDto> editPlayground(@RequestBody PlayGroundDto playGroundDto){
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(playGroundService.editPlayground(playGroundDto));
    }

    @CrossOrigin(CrossOriginUrl.URl)
    @PostMapping("/delete/{id}")
    public ResponseEntity<String> deletePlayground(@PathVariable Long id){
        playGroundService.delete(id);
        return ResponseEntity.status(HttpStatus.OK)
                .body("Playground " + id + " succesfully deleted!!");
    }
}
