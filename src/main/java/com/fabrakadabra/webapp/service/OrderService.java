package com.fabrakadabra.webapp.service;

import com.fabrakadabra.webapp.dto.order.*;
import com.fabrakadabra.webapp.dto.product.ProductDto;
import com.fabrakadabra.webapp.model.*;
import com.fabrakadabra.webapp.repository.OrderRepository;
import com.fabrakadabra.webapp.repository.ProductRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class OrderService {
    private OrderRepository orderRepository;
    private ProductService productService;
    private ProductRepository productRepository;

    public List<OrderDetails> getAll(){
        return orderRepository.findAll()
                .stream()
                .map(this::mapToDto)
                .collect(Collectors.toList());
    }

    public OrderDetails setOrderStatus(OrderStatusDto dto){
        Order order = orderRepository.findById(dto.getOrderId()).get();
        order.setStatus(OrderStatus.valueOf(dto.getStatus()));
        return mapToDto(orderRepository.save(order));
    }

    public List<OrderDetails> getAllByStatus(String status){
        return orderRepository.findAllByStatus(OrderStatus.valueOf(status))
                .stream()
                .map(this::mapToDto)
                .collect(Collectors.toList());
    }

    private OrderDetails mapToDto(Order order) {
        OrderDetails orderDetails = new OrderDetails();
        orderDetails.setOrderId(order.getID());
        orderDetails.setStatus(order.getStatus().toString());

        orderDetails.setItems(
                mapProductsToDto(
                        getProductsFromOrderItem(order)
                )
        );
        orderDetails.setCustomer(mapCustomerToDto(order.getCustomer()));
        return orderDetails;
    }

    private CustomerDto mapCustomerToDto(Customer customer){
        return CustomerDto.builder()
                .ID(customer.getID())
                .firstName(customer.getFirstName())
                .lastName(customer.getLastName())
                .email(customer.getEmail())
                .address(customer.getAddress())
                .phoneNumber(customer.getPhoneNumber())
                .build();
    }

    private List<ProductDto> mapProductsToDto(List<Product> products){
        List<ProductDto> dtos = new ArrayList<>();

        for(Product p : products){
            dtos.add(
              productService.mapToDto(p)
            );
        }
        return dtos;
    }

    private List<Product> getProductsFromOrderItem(Order order){
        List<Product> products = new ArrayList<>();

        for(OrderItem item : order.getOrderItems()){
            products.add(
                    productRepository.findById(item.getProductId()).get()
            );
        }
        return products;
    }

}
