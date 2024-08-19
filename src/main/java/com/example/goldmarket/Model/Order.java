package com.example.goldmarket.Model;

import com.fasterxml.jackson.annotation.JsonCreator;
import jakarta.persistence.*;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "orders")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_id")
    private Long orderId;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;

    @OneToMany
    private List<OrderItem> orderItems;

    @Enumerated(EnumType.STRING)
    private OrderStatus orderStatus;

    @Column(nullable = false, updatable = false) // Ensures the column is not updated after creation
    private LocalDateTime orderDate;

    @Column(name = "total_amount")
    @Positive(message = "Total amount must be positive")
    private double totalAmount;

    @PrePersist
    protected void onCreate() {
        if (this.orderDate == null) {
            this.orderDate = LocalDateTime.now(); // Set current date and time
        }
    }

    public enum OrderStatus {
        PENDING,
        PROCESSING,
        SHIPPED,
        DELIVERED,
        CANCELLED;

        @JsonCreator
        public static OrderStatus fromString(String key) {
            return key == null ? null : OrderStatus.valueOf(key.toUpperCase());
        }
    }
}



