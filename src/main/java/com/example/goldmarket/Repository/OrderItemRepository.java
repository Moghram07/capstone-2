package com.example.goldmarket.Repository;

import com.example.goldmarket.Model.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {

    OrderItem findByItemId(Long itemId);


}
