package com.fabrakadabra.webapp.controller;

import com.fabrakadabra.webapp.dto.order.OrderStatusDto;
import com.fabrakadabra.webapp.dto.order.OrderDetails;
import com.fabrakadabra.webapp.service.OrderService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/orders")
@AllArgsConstructor
public class OrderController {
    private OrderService orderService;

    @GetMapping("/all")
    public ResponseEntity<List<OrderDetails>> getOrder(){
        return ResponseEntity.status(HttpStatus.OK)
                .body(orderService.getAll());
    }

    @GetMapping("/{status}")
    public ResponseEntity<List<OrderDetails>> getAllByStatus(@PathVariable String status){
        return ResponseEntity.status(HttpStatus.OK)
                .body(orderService.getAllByStatus(status));
    }

    @PutMapping("/update")
    public ResponseEntity<OrderDetails> setStatus(@RequestBody OrderStatusDto dto){
        return ResponseEntity.status(HttpStatus.OK)
                .body(orderService.setOrderStatus(dto));
    }
}
