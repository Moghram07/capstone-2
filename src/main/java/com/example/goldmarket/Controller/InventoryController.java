package com.example.goldmarket.Controller;

import com.example.goldmarket.Model.Inventory;
import com.example.goldmarket.Service.InventoryService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/inventory")
@RequiredArgsConstructor
public class InventoryController {

    private final InventoryService inventoryService;

    @GetMapping("/get")
    public ResponseEntity getAllInventory() {
        return ResponseEntity.ok(inventoryService.getAllInventory());
    }

    @PostMapping("/add")
    public ResponseEntity addInventory(@RequestBody @Valid Inventory inventory, Errors errors) {
        if (errors.hasErrors()) {
            return ResponseEntity.status(400).body(errors.getFieldError().getDefaultMessage());
        }
        inventoryService.addInventory(inventory);
        return ResponseEntity.status(200).body("Inventory added");
    }

    @PutMapping("/update/{id}")
    public ResponseEntity updateInventory(@PathVariable long id, @RequestBody @Valid Inventory inventory, Errors errors) {
        if (errors.hasErrors()) {
            return ResponseEntity.status(400).body(errors.getFieldError().getDefaultMessage());
        }
        inventoryService.updateInventory(id, inventory);
        return ResponseEntity.status(200).body("Inventory updated");
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteInventory(@PathVariable long id) {
        inventoryService.deleteInventory(id);
        return ResponseEntity.status(200).body("Inventory deleted");
    }

    @GetMapping("/product/{productId}")
    public ResponseEntity getInventoryByProductId(@PathVariable long productId) {
        Inventory inventory = inventoryService.getInventoryByProductId(productId);
        return ResponseEntity.ok(inventory);
    }

    @GetMapping("/inStock/{inStock}")
    public ResponseEntity getInventoryByInStock(@PathVariable boolean inStock) {
        List<Inventory> inventoryList = inventoryService.getByInStock(inStock);
        return ResponseEntity.ok(inventoryList);
    }

    @GetMapping("/quantity/{quantity}")
    public ResponseEntity getInventoryByQuantity(@PathVariable int quantity) {
        List<Inventory> inventoryList = inventoryService.getByQuantity(quantity);
        return ResponseEntity.ok(inventoryList);
    }
}