package com.example.goldmarket.Repository;
import com.example.goldmarket.Model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {

    Product findById(long id);

    List<Product> findByColor(String color);

    List<Product> findByStoneType(Product.StoneType stoneType);

    List<Product> findByStoneShape(Product.StoneShape stoneShape);

    List<Product> findByStoneColor(Product.StoneColor stoneColor);

    List<Product> findByStoneWeight(double stoneWeight);

    List<Product> findByKarat(Product.GoldKarat karat);

    @Query("SELECT p FROM Product p WHERE p.price BETWEEN :minPrice AND :maxPrice")
    List<Product> findByPriceRange(double minPrice, double maxPrice);

    List<Product> findByType(String type);

    List<Product> findByWeight(double weight);

    List<Product> findBySellerId(Long sellerId);
}
