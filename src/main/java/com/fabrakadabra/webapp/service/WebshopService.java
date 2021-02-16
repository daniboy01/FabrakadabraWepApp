package com.fabrakadabra.webapp.service;

import com.fabrakadabra.webapp.dto.*;
import com.fabrakadabra.webapp.model.Customer;
import com.fabrakadabra.webapp.model.Order;
import com.fabrakadabra.webapp.model.OrderItem;
import com.fabrakadabra.webapp.repository.CustomerRepository;
import com.fabrakadabra.webapp.repository.OrderItemRepository;
import com.fabrakadabra.webapp.repository.OrderRepository;
import com.fabrakadabra.webapp.repository.PlayGroundRepository;
import com.google.gson.Gson;
import lombok.AllArgsConstructor;
import org.apache.commons.lang3.ArrayUtils;
import org.springframework.stereotype.Service;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;


@Service
@AllArgsConstructor
public class WebshopService {
    private OrderItemRepository orderItemRepository;
    private PlayGroundRepository playGroundRepository;
    private OrderRepository orderRepository;
    private CustomerRepository customerRepository;
    private EmailService emailService;
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


    public OrderResponse makeOrder(HttpServletResponse response, OrderDto orderDto) {
        Customer saveCutomer = customerRepository.save(mapCustomerDtotoModel(orderDto.getCustomerDto()));
        Order saveOrder = mapOrderDtotoModel(orderDto);
        saveOrder.setCustomer(saveCutomer);
        orderRepository.save(saveOrder);
        DateTimeFormatter dateFormat = DateTimeFormatter
                .ofLocalizedDate(FormatStyle.SHORT)
                .withLocale(Locale.ITALY)
                .withZone(ZoneId.systemDefault());

        HashMap<String, Object> model = new HashMap<>();
        model.put("orderID",saveOrder.getID());
        model.put("Name",saveCutomer.getFirstName() + " " +saveCutomer.getLastName());
        model.put("Address",saveCutomer.getAddress());
        model.put("PhoneNum",saveCutomer.getPhoneNumber());
        model.put("createdAt",dateFormat.format(saveOrder.getCreatedAt()));
        model.put("firstName",saveCutomer.getFirstName());

        emailService.sendEmail(saveCutomer.getEmail(),"FAbrakadabra",model);
        response.addCookie(new Cookie("cart",null));
        return new OrderResponse("Number " + saveOrder.getID() + " order recieved!",saveOrder.getID());
    }

    private Order mapOrderDtotoModel(OrderDto dto){
        return Order.builder()
                .ID(dto.getId())
                .createdAt(Instant.now())
                .customer(mapCustomerDtotoModel(dto.getCustomerDto()))
                .orderItems(mapOrderItemsToModels(dto.getOrderItems()))
                .build();
    }

    private List<OrderItem> mapOrderItemsToModels(List<OrderItemDto> orderItems) {
        List<OrderItem> items = new ArrayList<>();
        for (OrderItemDto dto : orderItems) {
            items.add(mapOrderItemDtoToModel(dto));
        }
        return items;
    }

    private Customer mapCustomerDtotoModel(CustomerDto customerDto) {
        return Customer.builder()
                .address(customerDto.getAddress())
                .email(customerDto.getEmail())
                .firstName(customerDto.getFirstName())
                .lastName(customerDto.getLastName())
                .phoneNumber(customerDto.getPhoneNumber())
                .build();

    }
}
