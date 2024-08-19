package com.example.goldmarket.Controller;

import com.example.goldmarket.Model.OrderItem;
import com.example.goldmarket.Service.OrderItemService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/orderItems")
@RequiredArgsConstructor
public class OrderItemController {

    private final OrderItemService orderItemService;

    @GetMapping("/get")
    public ResponseEntity getAllOrderItems() {
        List<OrderItem> orderItems = orderItemService.getAllOrderItems();
        return ResponseEntity.ok(orderItems);
    }

    @PostMapping("/add")
    public ResponseEntity addOrderItem(@RequestBody @Valid OrderItem orderItem, Errors errors) {
        if (errors.hasErrors()) {
            return ResponseEntity.status(400).body(errors.getFieldError().getDefaultMessage());
        }
        orderItemService.addOrderItem(orderItem);
        return ResponseEntity.status(200).body("Order item added");
    }

    @PutMapping("/update/{id}")
    public ResponseEntity updateOrderItem(@PathVariable long id, @RequestBody @Valid OrderItem orderItem, Errors errors) {
        if (errors.hasErrors()) {
            return ResponseEntity.status(400).body(errors.getFieldError().getDefaultMessage());
        }
        orderItemService.updateOrderItem(id, orderItem);
        return ResponseEntity.status(200).body("Order item updated");
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteOrderItem(@PathVariable long id) {
        orderItemService.deleteOrderItem(id);
        return ResponseEntity.status(200).body("Order item deleted");
    }

    @PostMapping("/buy/{productId}/{customerId}/{quantity}")
    public ResponseEntity buyProduct(@PathVariable long productId, @PathVariable long customerId, @PathVariable int quantity) {
        orderItemService.buyProduct(productId, customerId, quantity);
        return ResponseEntity.status(200).body("Product bought");
    }

}
