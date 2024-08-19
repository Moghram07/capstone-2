package com.example.goldmarket.Service;

import com.example.goldmarket.ApiException.ApiException;
import com.example.goldmarket.Model.Order;
import com.example.goldmarket.Model.Shipping;
import com.example.goldmarket.Repository.OrderRepository;
import com.example.goldmarket.Repository.ShippingRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;

    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }

    public void addOrder(Order order) {
        orderRepository.save(order);
    }

    public void updateOrder(Long id, Order order) {
        Order orders = orderRepository.findByOrderId(id);
        if (orders == null) {
            throw new ApiException("Order not found");
        }
        orders.setOrderItems(order.getOrderItems());
        orders.setOrderDate(order.getOrderDate());
        orders.setOrderStatus(order.getOrderStatus());
        orders.setCustomer(order.getCustomer());
        orderRepository.save(orders);
    }

    public void deleteOrder(Long id) {
        Order orders = orderRepository.findByOrderId(id);
        if (orders == null) {
            throw new ApiException("Order not found");
        }
        orderRepository.delete(orders);
    }

    public List<Order> getOrdersByCustomerId(Long customerId) {
        List<Order> orders = orderRepository.findByCustomerId(customerId);
        if (orders == null) {
            throw new ApiException("Order not found");
        }
        return orders;
    }

    public List<Order> getOrdersByOrderStatus(Order.OrderStatus orderStatus) {
        List<Order> orders = orderRepository.findByOrderStatus(orderStatus);
        if (orders == null) {
            throw new ApiException("Order not found");
        }
        return orders;
    }

    public List<Order> getOrderbyDateAfter(Date date) {
        List<Order> orders = orderRepository.findByOrderDateAfter(date);
        if (orders == null) {
            throw new ApiException("Order not found");
        }
        return orders;
    }

    public List<Order> getOrderbyDateBefore(Date date) {
        List<Order> orders = orderRepository.findByOrderDateBefore(date);
        if (orders == null) {
            throw new ApiException("Order not found");
        }
        return orders;
    }

    public List<Order> getOrderbyDateBetween(Date date1, Date date2) {
        List<Order> orders = orderRepository.findByOrderDateBetween(date1, date2);
        if (orders == null) {
            throw new ApiException("Order not found");
        }
        return orders;
    }

}
