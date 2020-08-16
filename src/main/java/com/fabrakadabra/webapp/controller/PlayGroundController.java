package com.fabrakadabra.webapp.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/playground")
public class PlayGroundController {

    @GetMapping("/1")
    public String index(){
        return "OK";
    }
}
