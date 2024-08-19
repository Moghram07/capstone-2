package com.example.goldmarket.Controller;

import com.example.goldmarket.Model.Customer;
import com.example.goldmarket.Service.CustomerService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/customers")
@RequiredArgsConstructor
public class CustomerController {

    private final CustomerService customerService;

    @GetMapping("/get")
    public ResponseEntity getAllCustomers() {
        return ResponseEntity.ok(customerService.getAllCustomers());
    }

    @PostMapping("/add")
    public ResponseEntity addCustomer(@RequestBody @Valid Customer customer, Errors errors) {
        if (errors.hasErrors()) {
            return ResponseEntity.status(400).body(errors.getFieldError().getDefaultMessage());
        }
        customerService.addCustomer(customer);
        return ResponseEntity.status(200).body("Customer added");
    }

    @PutMapping("/update/{id}")
    public ResponseEntity updateCustomer(@PathVariable Long id, @RequestBody @Valid Customer customer, Errors errors) {
        if (errors.hasErrors()) {
            return ResponseEntity.status(400).body(errors.getFieldError().getDefaultMessage());
        }
        customerService.updateCustomer(id, customer);
        return ResponseEntity.status(200).body("Customer updated");
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteCustomer(@PathVariable Long id) {
        customerService.deleteCustomer(id);
        return ResponseEntity.status(200).body("Customer deleted");
    }

    @GetMapping("/searchUsername/{username}")
    public ResponseEntity findByUsername(@PathVariable String username) {
        return ResponseEntity.ok(customerService.getByUsername(username));
    }

    @GetMapping("/searchEmail/{email}")
    public ResponseEntity getByEmail(@PathVariable String email) {
        return ResponseEntity.ok(customerService.getByEmail(email));
    }

    @GetMapping("/searchPhone/{phone}")
    public ResponseEntity findByPhone(@PathVariable String phone) {
        return ResponseEntity.ok(customerService.getByPhone(phone));
    }

    @GetMapping("/searchId/{id}")
    public ResponseEntity findById(@PathVariable Long id) {
        return ResponseEntity.ok(customerService.getById(id));
    }
}