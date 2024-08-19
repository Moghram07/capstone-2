package com.example.goldmarket.Model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name = "customers")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Name is required")
    @Size(max = 100, message = "Name cannot exceed 100 characters")
    @Column(unique = true, nullable = false)
    private String name;

    @NotBlank(message = "Email is required")
    @Email(message = "Invalid email address")
    @Size(max = 100, message = "Email cannot exceed 100 characters")
    @Column(unique = true, nullable = false)
    private String email;

    @NotBlank(message = "Phone number is required")
    @Pattern(regexp = "^05[0-9]{8}$", message = "Phone number must start with 05 and be followed by 8 digits.")
    @Column(unique = true, nullable = false)
    private String phone;

    @NotBlank(message = "Username is required")
    @Size(min = 3, max = 50, message = "Username must be between 3 and 50 characters")
    @Column(unique = true, nullable = false)
    private String username;

    @NotBlank(message = "Password is required")
    @Pattern(regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=]).{8,}$",
            message = "Password must be at least 8 characters long and include at least one digit, " +
                    "one lowercase letter, one uppercase letter, and one special character (@#$%^&+=).")
    @Column(nullable = false)
    private String password;

    @NotBlank(message = "Address is required")
    @Size(max = 255, message = "Address cannot exceed 255 characters")
    @Column(nullable = false)
    private String address;

    @OneToMany
    private List<Order> orders;

    @DecimalMin(value = "0.0", inclusive = true, message = "Balance must be zero or positive")
    @Column(nullable = false)
    private double balance;

}

