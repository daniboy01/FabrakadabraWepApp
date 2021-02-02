package com.fabrakadabra.webapp.controller;

import com.fabrakadabra.webapp.dto.OrderDto;
import com.fabrakadabra.webapp.dto.OrderItemDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/shop")
public class WebshopController {

    //add to cart, cookies
    @PostMapping("/addToCart")
    public ResponseEntity<String> addToCart(HttpServletResponse response, @RequestBody OrderItemDto orderItemDto){
        return null;
    }

    //remove from cart, remove cookies
    public ResponseEntity<String> removeFromCart(HttpServletResponse response, @RequestBody OrderItemDto orderItemDto){
        return null;
    }

    //make order
    public ResponseEntity<String> makeOrder(HttpServletResponse response, @RequestBody OrderDto orderDto){
        return null;
    }

    //
}
