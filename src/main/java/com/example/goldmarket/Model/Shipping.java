package com.example.goldmarket.Model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Entity
@Table(name = "shippings")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Shipping {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long shippingId;

    @NotBlank(message = "Carrier name cannot be empty")
    @Size(max = 100, message = "Carrier name cannot exceed 100 characters")
    private String carrierName;

    @NotBlank(message = "Tracking number cannot be empty")
    @Pattern(regexp = "^[A-Z0-9]{10,20}$", message = "Tracking number must be between 10 and 20 alphanumeric characters")
    private String trackingNumber;

    @OneToMany(mappedBy = "shippingDetails", cascade = CascadeType.ALL)
    @JsonManagedReference // Prevents infinite recursion when serializing
    private List<OrderItem> orderItems;

    @DecimalMin(value = "0.0", inclusive = true, message = "Shipping cost must be zero or positive")
    private double shippingCost;

    @NotBlank(message = "Delivery address cannot be empty")
    @Size(max = 255, message = "Delivery address cannot exceed 255 characters")
    private String deliveryAddress;

    @Temporal(TemporalType.DATE)
    private Date expectedDeliveryDate;

    @Enumerated(EnumType.STRING)
    private ShippingStatus status;

    @PrePersist
    protected void onCreate() {
        if (this.status == null) {
            this.status = ShippingStatus.INITIATED;
        }
    }

    public enum ShippingStatus {
        INITIATED,
        IN_TRANSIT,
        DELIVERED,
        RETURNED;
        @JsonCreator
        public static ShippingStatus fromString(String key) {
            return key == null ? null : ShippingStatus.valueOf(key.toUpperCase());
        }
    }

}



