package com.example.goldmarket.Service;

import com.example.goldmarket.ApiException.ApiException;
import com.example.goldmarket.Model.Product;
import com.example.goldmarket.Repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;

    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    public void addProduct(Product product) {
        productRepository.save(product);
    }

    public void updateProduct(long id, Product product) {
        Product p = productRepository.findById(id);
        if(p == null) {
            throw new ApiException("Product not found");
        }
        p.setColor(product.getColor());
        p.setSeller(product.getSeller());
        p.setPrice(product.getPrice());
        p.setKarat(product.getKarat());
        p.setStoneColor(product.getStoneColor());
        p.setStoneShape(product.getStoneShape());
        p.setStoneType(product.getStoneType());
        p.setWeight(product.getWeight());
        p.setType(product.getType());
        p.setInStock(product.isInStock());
        productRepository.save(p);
    }

    public void deleteProduct(long id) {
        Product p = productRepository.findById(id);
        if (p == null) {
            throw new ApiException("Product not found");
        }
        productRepository.delete(p);
    }

    public List<Product> getBySellerId(Long sellerId){
        List<Product> products = productRepository.findBySellerId(sellerId);
        if(products.isEmpty()) {
            throw new ApiException("Product not found");
        }
        return products;
    }

    public List<Product> getByType(String type) {
        List<Product> products = productRepository.findByType(type);
        if (products.isEmpty()) {
            throw new ApiException("No products found for this type");
        }
        return products;
    }

    public List<Product> getByStoneType(Product.StoneType stoneType) {
        List<Product> products = productRepository.findByStoneType(stoneType);
        if (products.isEmpty()) {
            throw new ApiException("No products found for this stone type");
        }
        return products;
    }

    public List<Product> getByStoneShape(Product.StoneShape stoneShape) {
        List<Product> products = productRepository.findByStoneShape(stoneShape);
        if (products.isEmpty()) {
            throw new ApiException("No products found for this stone type");
        }
        return products;
    }

    public List<Product> getByStoneColor(Product.StoneColor stoneColor) {
        List<Product> products = productRepository.findByStoneColor(stoneColor);
        if (products.isEmpty()) {
            throw new ApiException("No products found for this stone color");
        }
        return products;
    }

    public List<Product> getByColor(String color) {
        List<Product> products = productRepository.findByColor(color);
        if (products.isEmpty()) {
            throw new ApiException("No products found for this color");
        }
        return products;
    }

    public List<Product> getByKarat(Product.GoldKarat karat) {
        List<Product> products = productRepository.findByKarat(karat);
        if (products.isEmpty()) {
            throw new ApiException("No products found for this karat");
        }
        return products;
    }

    public List<Product> getByPrice(double minPrice, double maxPrice) {
        List<Product> products = productRepository.findByPriceRange(minPrice, maxPrice);
        if (products.isEmpty()) {
            throw new ApiException("No products found for this price");
        }
        return products;
    }

    public List<Product> getByWeight(double weight) {
        List<Product> products = productRepository.findByWeight(weight);
        if (products.isEmpty()) {
            throw new ApiException("No products found for this weight");
        }
        return products;
    }

    public List<Product> getByStoneWeight(double stoneWeight) {
        List<Product> products = productRepository.findByStoneWeight(stoneWeight);
        if (products.isEmpty()) {
            throw new ApiException("No products found for this stone weight");
        }
        return products;
    }

    public Product getById(long id) {
        Product p = productRepository.findById(id);
        if (p == null) {
            throw new ApiException("Product not found");
        }
        return p;
    }

}
