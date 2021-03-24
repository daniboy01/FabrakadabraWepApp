package com.fabrakadabra.webapp.repository;

import com.fabrakadabra.webapp.model.Order;
import com.fabrakadabra.webapp.model.OrderStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
    List<Order> findAllByStatus(OrderStatus status);
}
