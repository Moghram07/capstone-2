package com.example.goldmarket.Controller;

import com.example.goldmarket.Model.Seller;
import com.example.goldmarket.Service.SellerService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/sellers")
@RequiredArgsConstructor
public class SellerController {

    private final SellerService sellerService;

    @GetMapping("/get")
    public ResponseEntity getAllSellers() {
        return ResponseEntity.ok(sellerService.getAllSellers());
    }

    @PostMapping("/add")
    public ResponseEntity addSeller(@RequestBody @Valid Seller seller, Errors errors) {
        if (errors.hasErrors()) {
            return ResponseEntity.status(400).body(errors.getFieldError().getDefaultMessage());
        }
        sellerService.addSeller(seller);
        return ResponseEntity.status(200).body("Seller added");
    }

    @PutMapping("/update/{id}")
    public ResponseEntity updateSeller(@PathVariable Long id, @RequestBody @Valid Seller seller, Errors errors) {
        if (errors.hasErrors()) {
            return ResponseEntity.status(400).body(errors.getFieldError().getDefaultMessage());
        }
        sellerService.updateSeller(id, seller);
        return ResponseEntity.status(200).body("Seller updated");
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteSeller(@PathVariable Long id) {
        sellerService.deleteSeller(id);
        return ResponseEntity.status(200).body("Seller deleted");
    }

    @GetMapping("/searchUsername/{username}")
    public ResponseEntity findByUsername(@PathVariable String username) {
        return ResponseEntity.ok(sellerService.getByUsername(username));
    }

    @GetMapping("/searchEmail/{email}")
    public ResponseEntity getByEmail(@PathVariable String email) {
        return ResponseEntity.ok(sellerService.getByEmail(email));
    }

    @GetMapping("/searchPhone/{phone}")
    public ResponseEntity findByPhone(@PathVariable String phone) {
        return ResponseEntity.ok(sellerService.getByPhone(phone));
    }
}
