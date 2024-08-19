package com.example.goldmarket.Repository;

import com.example.goldmarket.Model.Inventory;
import com.example.goldmarket.Model.Product;
import jakarta.persistence.criteria.CriteriaBuilder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface InventoryRepository extends JpaRepository<Inventory, Long> {

    Inventory findById(long id);

    @Query("SELECT i FROM Inventory i WHERE i.product.id = :productId")
    Inventory findByProductId(@Param("productId") Long productId);

    List<Inventory> findByInStock(boolean inStock);

    List<Inventory> findByQuantity(int quantity);
}
