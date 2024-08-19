package com.example.goldmarket.Repository;

import com.example.goldmarket.Model.Seller;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SellerRepository extends JpaRepository<Seller, Long> {
    Seller findById(long id);

    Seller findByUsername(String username);

    Seller findByEmail(String email);

    Seller findByPhone(String phone);
}
