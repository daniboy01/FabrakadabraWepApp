package com.fabrakadabra.webapp.controller;

import com.fabrakadabra.webapp.dto.PlayGroundDto;
import com.fabrakadabra.webapp.model.PlayGround;
import com.fabrakadabra.webapp.service.PlayGroundService;
import com.google.gson.Gson;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/cookies")
@AllArgsConstructor
public class CookieDemoController {
    private PlayGroundService playGroundService;
    private Gson gson;

    @GetMapping("/{id}")
    public ResponseEntity<PlayGroundDto> getPlaygroundById(HttpServletResponse response, @PathVariable Long id) throws UnsupportedEncodingException {
        PlayGroundDto dto = playGroundService.getPlaygroundById(id);

        Cookie cookie = new Cookie("playgroundinfo", URLEncoder.encode(gson.toJson(dto),"UTF-8"));
        response.addCookie(cookie);
        return ResponseEntity.status(HttpStatus.OK)
                .body(dto);
    }

    @GetMapping("/getCurrent")
    public ResponseEntity<PlayGroundDto> getCookie(@CookieValue(name = "playgroundinfo",defaultValue = "")String cookieDto){
        System.out.println(cookieDto);
        PlayGroundDto dtoFromCookie = gson.fromJson(cookieDto,PlayGroundDto.class);
        return ResponseEntity.status(HttpStatus.OK)
                .body(dtoFromCookie);
    }

    @GetMapping("/addALot")
    public ResponseEntity<String> addDtos(HttpServletResponse response) throws UnsupportedEncodingException {
        List<PlayGroundDto>dtos = new ArrayList<>();
        dtos.add(new PlayGroundDto(Integer.toUnsignedLong(11),"demo11",new ArrayList<>(),11));
        dtos.add(new PlayGroundDto(Integer.toUnsignedLong(12),"demo12",new ArrayList<>(),11));
        dtos.add(new PlayGroundDto(Integer.toUnsignedLong(13),"demo13",new ArrayList<>(),11));
        Cookie cookiePlaygrounds = new Cookie("playgrounds", URLEncoder.encode(gson.toJson(dtos),"UTF-8"));
        response.addCookie(cookiePlaygrounds);
        return ResponseEntity.status(HttpStatus.OK)
                .body("OK");
    }

    @GetMapping("/getAllDto")
    public ResponseEntity<PlayGroundDto[]> getAllDto(@CookieValue("playgrounds")String playgrounds){
        PlayGroundDto[] dtos = gson.fromJson(playgrounds,PlayGroundDto[].class);
        return ResponseEntity.status(HttpStatus.OK)
                .body(dtos);
    }

}
