package com.example.goldmarket.Repository;

import com.example.goldmarket.Model.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Long> {

    Order findByOrderId(Long id);

    List<Order> findByCustomerId(Long customerId);

    List<Order> findByOrderStatus(Order.OrderStatus orderStatus);

    List<Order> findByOrderDateAfter(Date orderDate);

    List<Order> findByOrderDateBefore(Date orderDate);

    List<Order> findByOrderDateBetween(Date startDate, Date endDate);

}
