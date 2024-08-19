package com.example.goldmarket.Controller;

import com.example.goldmarket.Model.Order;
import com.example.goldmarket.Service.OrderService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/api/v1/orders")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;

    @GetMapping("/get")
    public ResponseEntity getAllOrders() {
        return ResponseEntity.ok(orderService.getAllOrders());
    }

    @PostMapping("/add")
    public ResponseEntity addOrder(@RequestBody @Valid Order order, Errors errors) {
        if (errors.hasErrors()) {
            return ResponseEntity.status(400).body(errors.getFieldError().getDefaultMessage());
        }
        orderService.addOrder(order);
        return ResponseEntity.status(200).body("Order added");
    }

    @PutMapping("/update/{id}")
    public ResponseEntity updateOrder(@PathVariable long id, @RequestBody @Valid Order order, Errors errors) {
        if (errors.hasErrors()) {
            return ResponseEntity.status(400).body(errors.getFieldError().getDefaultMessage());
        }
        orderService.updateOrder(id, order);
        return ResponseEntity.status(200).body("Order updated");
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteOrder(@PathVariable long id) {
        orderService.deleteOrder(id);
        return ResponseEntity.status(200).body("Order deleted");
    }

    @GetMapping("/customer/{customerId}")
    public ResponseEntity getOrdersByCustomerId(@PathVariable long customerId) {
        List<Order> orders = orderService.getOrdersByCustomerId(customerId);
        return ResponseEntity.ok(orders);
    }

    @GetMapping("/status/{orderStatus}")
    public ResponseEntity getOrdersByOrderStatus(@PathVariable Order.OrderStatus orderStatus) {
        List<Order> orders = orderService.getOrdersByOrderStatus(orderStatus);
        return ResponseEntity.ok(orders);
    }

    @GetMapping("/dateAfter/{date}")
    public ResponseEntity getOrderByDateAfter(@PathVariable Date date) {
        List<Order> orders = orderService.getOrderbyDateAfter(date);
        return ResponseEntity.ok(orders);
    }

    @GetMapping("/dateBefore/{date}")
    public ResponseEntity getOrderByDateBefore(@PathVariable Date date) {
        List<Order> orders = orderService.getOrderbyDateBefore(date);
        return ResponseEntity.ok(orders);
    }

    @GetMapping("/dateBetween/{date1}/{date2}")
    public ResponseEntity getOrderByDateBetween(@PathVariable Date date1, @PathVariable Date date2) {
        List<Order> orders = orderService.getOrderbyDateBetween(date1, date2);
        return ResponseEntity.ok(orders);
    }

}

