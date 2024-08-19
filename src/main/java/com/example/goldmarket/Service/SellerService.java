package com.example.goldmarket.Service;

import com.example.goldmarket.ApiException.ApiException;
import com.example.goldmarket.Model.Seller;
import com.example.goldmarket.Repository.SellerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SellerService {

    private final SellerRepository sellerRepository;

    public List<Seller> getAllSellers() {
        return sellerRepository.findAll();
    }

    public void addSeller(Seller seller) {
        sellerRepository.save(seller);
    }

    public void updateSeller(long id, Seller updatedSeller) {
        Seller existingSeller = sellerRepository.findById(id);
        if (existingSeller == null) {
            throw new ApiException("Seller with id " + id + " not found");
        }

        if (updatedSeller.getName() != null) {
            existingSeller.setName(updatedSeller.getName());
        }
        if (updatedSeller.getUsername() != null) {
            existingSeller.setUsername(updatedSeller.getUsername());
        }
        if (updatedSeller.getPassword() != null) {
            existingSeller.setPassword(updatedSeller.getPassword());
        }
        if (updatedSeller.getEmail() != null) {
            existingSeller.setEmail(updatedSeller.getEmail());
        }
        if (updatedSeller.getPhone() != null) {
            existingSeller.setPhone(updatedSeller.getPhone());
        }
        if (updatedSeller.getAddress() != null) {
            existingSeller.setAddress(updatedSeller.getAddress());
        }

        sellerRepository.save(existingSeller);
    }

    public void deleteSeller(long id) {
        Seller seller = sellerRepository.findById(id);
        if (seller == null) {
            throw new ApiException("Seller not found");
        }
        sellerRepository.delete(seller);
    }

    public Seller getByUsername(String username) {
        Seller seller = sellerRepository.findByUsername(username);
        if (seller == null) {
            throw new ApiException("Seller not found");
        }
        return seller;
    }

    public Seller getByEmail(String email) {
        Seller seller = sellerRepository.findByEmail(email);
        if (seller == null) {
            throw new ApiException("Seller not found");
        }
        return seller;
    }

    public Seller getByPhone(String phone) {
        Seller seller = sellerRepository.findByPhone(phone);
        if (seller == null) {
            throw new ApiException("Seller not found");
        }
        return seller;
    }
}