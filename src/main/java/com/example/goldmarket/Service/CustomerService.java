package com.example.goldmarket.Service;

import com.example.goldmarket.ApiException.ApiException;
import com.example.goldmarket.Model.Customer;
import com.example.goldmarket.Repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CustomerService {

    private final CustomerRepository customerRepository;

    public List<Customer> getAllCustomers() {
        return customerRepository.findAll();
    }

    public void addCustomer(Customer customer) {
        customerRepository.save(customer);
    }

    public void updateCustomer(long id, Customer updatedCustomer) {
        Customer existingCustomer = customerRepository.findById(id);
        if (existingCustomer == null) {
            throw new ApiException("Customer with id " + id + " not found");
        }

        if (updatedCustomer.getName() != null) {
            existingCustomer.setName(updatedCustomer.getName());
        }
        if (updatedCustomer.getUsername() != null) {
            existingCustomer.setUsername(updatedCustomer.getUsername());
        }
        if (updatedCustomer.getPassword() != null) {
            existingCustomer.setPassword(updatedCustomer.getPassword());
        }
        if (updatedCustomer.getEmail() != null) {
            existingCustomer.setEmail(updatedCustomer.getEmail());
        }
        if (updatedCustomer.getPhone() != null) {
            existingCustomer.setPhone(updatedCustomer.getPhone());
        }
        if (updatedCustomer.getAddress() != null) {
            existingCustomer.setAddress(updatedCustomer.getAddress());
        }
        existingCustomer.setBalance(updatedCustomer.getBalance());

        customerRepository.save(existingCustomer);
    }

    public void deleteCustomer(long id) {
        Customer customer = customerRepository.findById(id);
        if (customer == null) {
            throw new ApiException("Customer not found");
        }
        customerRepository.delete(customer);
    }

    public Customer getByUsername(String username) {
        Customer customer = customerRepository.findByUsername(username);
        if (customer == null) {
            throw new ApiException("Customer not found");
        }
        return customer;
    }

    public Customer getByEmail(String email) {
        Customer customer = customerRepository.findByEmail(email);
        if (customer == null) {
            throw new ApiException("Customer not found");
        }
        return customer;
    }

    public Customer getByPhone(String phone) {
        Customer customer = customerRepository.findByPhone(phone);
        if (customer == null) {
            throw new ApiException("Customer not found");
        }
        return customer;
    }

    public Customer getById(long id) {
        Customer customer = customerRepository.findById(id);
        if (customer == null) {
            throw new ApiException("Customer not found");
        }
        return customer;
    }
}