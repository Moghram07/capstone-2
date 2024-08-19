package com.example.goldmarket.Controller;

import com.example.goldmarket.Model.Product;
import com.example.goldmarket.Service.ProductService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/products")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    @GetMapping("/get")
    public ResponseEntity getAllProducts() {
        return ResponseEntity.ok(productService.getAllProducts());
    }

    @PostMapping("/add")
    public ResponseEntity addProduct(@Valid @RequestBody Product product, Errors errors) {
        if (errors.hasErrors()) {
            return ResponseEntity.status(400).body(errors.getFieldError().getDefaultMessage());
        }
        productService.addProduct(product);
        return ResponseEntity.status(200).body("Product added");
    }

    @PutMapping("/update/{id}")
    public ResponseEntity updateProduct(@PathVariable long id, @RequestBody Product product, Errors errors) {
        if (errors.hasErrors()) {
            return ResponseEntity.status(400).body(errors.getFieldError().getDefaultMessage());
        }
        productService.updateProduct(id, product);
        return ResponseEntity.status(200).body("Product updated");
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteProduct(@PathVariable long id) {
        productService.deleteProduct(id);
        return ResponseEntity.status(200).body("Product deleted");
    }

    @GetMapping("/seller/{sellerId}")
    public ResponseEntity getBySellerId(@PathVariable Long sellerId) {
        List<Product> products = productService.getBySellerId(sellerId);
        return ResponseEntity.ok(products);
    }

    @GetMapping("/type/{type}")
    public ResponseEntity getByType(@PathVariable String type) {
        List<Product> products = productService.getByType(type);
        return ResponseEntity.ok(products);
    }

    @GetMapping("/stoneType/{stoneType}")
    public ResponseEntity getByStoneType(@PathVariable String stoneType) {
        List<Product> products = productService.getByStoneType(Product.StoneType.valueOf(stoneType.toUpperCase()));
        return ResponseEntity.ok(products);
    }

    @GetMapping("/stoneShape/{stoneShape}")
    public ResponseEntity getByStoneShape(@PathVariable String stoneShape) {
        List<Product> products = productService.getByStoneShape(Product.StoneShape.valueOf(stoneShape.toUpperCase()));
        return ResponseEntity.ok(products);
    }

    @GetMapping("/stoneColor/{stoneColor}")
    public ResponseEntity getByStoneColor(@PathVariable String stoneColor) {
        List<Product> products = productService.getByStoneColor(Product.StoneColor.valueOf(stoneColor.toUpperCase()));
        return ResponseEntity.ok(products);
    }

    @GetMapping("/color/{color}")
    public ResponseEntity getByColor(@PathVariable String color) {
        List<Product> products = productService.getByColor(color);
        return ResponseEntity.ok(products);
    }

    @GetMapping("/karat/{karat}")
    public ResponseEntity getByKarat(@PathVariable Product.GoldKarat karat) {
        List<Product> products = productService.getByKarat(karat);
        return ResponseEntity.ok(products);
    }

    @GetMapping("/price/{min}/{max}")
    public ResponseEntity getByPriceRange(@PathVariable double min, @PathVariable double max) {
        List<Product> products = productService.getByPrice(min, max);
        return ResponseEntity.ok(products);
    }

    @GetMapping("/weight/{weight}")
    public ResponseEntity getByWeight(@PathVariable double weight) {
        List<Product> products = productService.getByWeight(weight);
        return ResponseEntity.ok(products);
    }

    @GetMapping("/stoneWeight/{stoneWeight}")
    public ResponseEntity getByStoneWeight(@PathVariable double stoneWeight) {
        List<Product> products = productService.getByStoneWeight(stoneWeight);
        return ResponseEntity.ok(products);
    }
}
