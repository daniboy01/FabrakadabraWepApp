package com.fabrakadabra.webapp.service;

import com.fabrakadabra.webapp.dto.AddedToCartResponse;
import com.fabrakadabra.webapp.dto.OrderItemDto;
import com.fabrakadabra.webapp.model.OrderItem;
import com.fabrakadabra.webapp.repository.OrderItemRepository;
import com.fabrakadabra.webapp.repository.PlayGroundRepository;
import com.google.gson.Gson;
import lombok.AllArgsConstructor;
import org.apache.commons.lang3.ArrayUtils;
import org.springframework.stereotype.Service;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.time.Instant;
import java.util.List;
import java.util.UUID;

@Service
@AllArgsConstructor
public class WebshopService {
    private OrderItemRepository orderItemRepository;
    private PlayGroundRepository playGroundRepository;
    private Gson gson;

    public AddedToCartResponse addToShoppingCart(HttpServletResponse response, List<OrderItemDto> orderItemDtos) {
        for (OrderItemDto dto:orderItemDtos){
            var obj = orderItemRepository.save(mapOrderItemDtoToModel(dto));
            dto.setId(obj.getId());
        }
        try {
            Cookie cookie = new Cookie("cart", URLEncoder.encode(gson.toJson(orderItemDtos),"UTF-8"));
            response.addCookie(cookie);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        return new AddedToCartResponse("OrderItems added to shopping cart!!!");
    }


    private OrderItem mapOrderItemDtoToModel(OrderItemDto orderItemDto) {
        return OrderItem.builder()
                .Id(orderItemDto.getId())
                .createdAt(Instant.now())
                .playGround(playGroundRepository.findById(orderItemDto.getPlayGroundId()).get())
                .build();
    }


    public OrderItemDto[] removeFromCart(HttpServletResponse response, OrderItemDto orderItemDto, String items) {
        OrderItemDto[] dtos = gson.fromJson(items,OrderItemDto[].class);
        for(int i = 0; i < dtos.length; i++ ){
            if (dtos[i].getId() == orderItemDto.getId()){
                dtos = ArrayUtils.remove(dtos,i);
                break;
            }
        }
        response.addCookie(new Cookie("cart",URLEncoder.encode(gson.toJson(dtos))));
        return dtos;
    }
}
