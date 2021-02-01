package com.fabrakadabra.webapp.controller;

import com.fabrakadabra.webapp.dto.PlayGroundDto;
import com.fabrakadabra.webapp.service.PlayGroundService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@RestController
@RequestMapping("/api/playground")
@AllArgsConstructor
public class PlayGroundController {
    private final PlayGroundService playGroundService;

    @GetMapping("/getAll")
    public ResponseEntity<List<PlayGroundDto>> getAll(){
        return  ResponseEntity
                .status(HttpStatus.OK)
                .body(playGroundService.getAll());
    }

    @PostMapping("/createPlayground")
    public ResponseEntity<PlayGroundDto> createPlayground(HttpServletResponse response, @RequestBody PlayGroundDto playGroundDto){
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(playGroundService.save(playGroundDto));
    }

    @GetMapping("/{id}")
    public ResponseEntity<PlayGroundDto> getPlayground(@PathVariable Long id){
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(playGroundService.getPlaygroundById(id));
    }

    @PostMapping("/edit")
    public ResponseEntity<PlayGroundDto> editPlayground(@RequestBody PlayGroundDto playGroundDto){
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(playGroundService.editPlayground(playGroundDto));
    }

    @PostMapping("/delete/{id}")
    public ResponseEntity<String> deletePlayground(@PathVariable Long id){
        playGroundService.delete(id);
        return ResponseEntity.status(HttpStatus.OK)
                .body("Playground " + id + " succesfully deleted!!");
    }
}
