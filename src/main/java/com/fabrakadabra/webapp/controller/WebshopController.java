package com.fabrakadabra.webapp.controller;
import com.fabrakadabra.webapp.dto.AddedToCartResponse;
import com.fabrakadabra.webapp.dto.OrderDto;
import com.fabrakadabra.webapp.dto.OrderItemDto;
import com.fabrakadabra.webapp.service.WebshopService;
import com.google.gson.Gson;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@RestController
@RequestMapping("/shop")
@AllArgsConstructor
public class WebshopController {
    private WebshopService webshopService;

    @PostMapping("/addToCart")
    public ResponseEntity<AddedToCartResponse> addToCart(HttpServletResponse response,
                                                         @RequestBody List<OrderItemDto> orderItemDtos){
        return ResponseEntity.status(HttpStatus.OK)
                .body(webshopService.addToShoppingCart(response,orderItemDtos));
    }

    @GetMapping("/readCookies")
    public ResponseEntity<OrderItemDto[]> readCookies(@CookieValue("cart") String items){
        Gson gson = new Gson();
        OrderItemDto[] dtos = gson.fromJson(items,OrderItemDto[].class);
        return ResponseEntity.status(HttpStatus.OK)
                .body(dtos);
    }

    @PostMapping("/removeItem")
    public ResponseEntity<OrderItemDto[]> removeFromCart(HttpServletResponse response,
                                                 @RequestBody OrderItemDto orderItemDto,
                                                 @CookieValue("cart") String items){
        return ResponseEntity.status(HttpStatus.OK)
                .body(webshopService.removeFromCart(response,orderItemDto,items));
    }

    //make order
    public ResponseEntity<String> makeOrder(HttpServletResponse response,
                                            @RequestBody OrderDto orderDto){
        return null;
    }


}
