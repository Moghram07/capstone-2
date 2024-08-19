package com.example.goldmarket.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "order_items")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long itemId;

    @ManyToOne
    @JoinColumn(name = "product_id", nullable = false) // Ensures that product is always set
    private Product product;

    @ManyToOne
    @JoinColumn(name = "customer_id", nullable = false) // Ensures that customer is always set
    private Customer customer;

    @ManyToOne
    @JoinColumn(name = "shipping_id")
    @JsonIgnore
    private Shipping shippingDetails;

    @Min(value = 1, message = "Quantity must be at least 1")
    private int quantity;

    public double getTotalPrice() {
        return this.quantity * this.product.getPrice();
    }
}
