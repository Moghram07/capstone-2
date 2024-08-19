package com.example.goldmarket.Repository;

import com.example.goldmarket.Model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {

    Customer findById(long id);

    Customer findByUsername(String username);

    Customer findByEmail(String email);

    Customer findByPhone(String phone);

}
