package com.example.goldmarket.Service;

import com.example.goldmarket.ApiException.ApiException;
import com.example.goldmarket.Model.Inventory;
import com.example.goldmarket.Model.Product;
import com.example.goldmarket.Repository.InventoryRepository;
import com.example.goldmarket.Repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@RequiredArgsConstructor
public class InventoryService {

    private final InventoryRepository inventoryRepository;
    private final ProductRepository productRepository;

    public List<Inventory> getAllInventory(){
        return inventoryRepository.findAll();
    }

    public void addInventory(Inventory inventory){
        // Retrieve the Product based on the ID
        Long productId = inventory.getProduct().getId();
        Product product = productRepository.findById(inventory.getProduct().getId()).orElseThrow(() -> new ApiException("Product not found"));
        inventory.setProduct(product);
        inventoryRepository.save(inventory);
    }

    public void updateInventory(long id, Inventory inventory){
        Inventory i = inventoryRepository.findById(id);
        if(i == null){
            throw new ApiException("Inventory not found");
        }
        i.setQuantity(inventory.getQuantity());
        i.setProduct(inventory.getProduct());
        i.setInStock(inventory.isInStock());
        inventoryRepository.save(i);
    }

    public void deleteInventory(long id){
        Inventory i = inventoryRepository.findById(id);
        if(i == null){
            throw new ApiException("Inventory not found");
        }
        inventoryRepository.delete(i);
    }

    public Inventory getInventoryByProductId(long productId){
        Inventory i = inventoryRepository.findByProductId(productId);
        if(i == null){
            throw new ApiException("Inventory not found");
        }
        return i;
    }

    public List<Inventory> getByInStock(boolean inStock){
        List<Inventory> inventoryList = inventoryRepository.findByInStock(inStock);
        if(inventoryList == null){
            throw new ApiException("Inventory not found");
        }
        return inventoryList;
    }

    public List<Inventory> getByQuantity(int quantity){
        List<Inventory> inventoryList = inventoryRepository.findByQuantity(quantity);
        if(inventoryList == null){
            throw new ApiException("Inventory not found");
        }
        return inventoryList;
    }
}
