package com.example.goldmarket.Controller;

import com.example.goldmarket.Model.Shipping;
import com.example.goldmarket.Service.ShippingService;
import jakarta.persistence.Entity;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/api/v1/shipping")
@RequiredArgsConstructor
public class ShippingController {

    private final ShippingService shippingService;

    @GetMapping("/get")
    public ResponseEntity getAllShipping() {
        return ResponseEntity.ok(shippingService.getAllShipping());
    }

    @PostMapping("/add")
    public ResponseEntity addShipping(@RequestBody @Valid Shipping shipping, Errors errors) {
        if (errors.hasErrors()) {
            return ResponseEntity.status(400).body(errors.getFieldError().getDefaultMessage());
        }
        shippingService.addShipping(shipping);
        return ResponseEntity.status(200).body("Shipping added");
    }

    @PutMapping("/update/{id}")
    public ResponseEntity updateShipping(@PathVariable long id, @RequestBody @Valid Shipping shipping, Errors errors) {
        if (errors.hasErrors()) {
            return ResponseEntity.status(400).body(errors.getFieldError().getDefaultMessage());
        }
        shippingService.updateShipping(id, shipping);
        return ResponseEntity.status(200).body("Shipping updated");
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteShipping(@PathVariable long id) {
        shippingService.deleteShipping(id);
        return ResponseEntity.status(200).body("Shipping deleted");
    }

    @GetMapping("/tracking/{trackingNumber}")
    public ResponseEntity trackShipping(@PathVariable String trackingNumber) {
        return ResponseEntity.ok(shippingService.getbyTrackingNumber(trackingNumber));
    }

    @GetMapping("/carrier/{carrierName}")
    public ResponseEntity getShippingByCarrierName(@PathVariable String carrierName) {
        List<Shipping> shippings = shippingService.getShippingByCarrierName(carrierName);
        return ResponseEntity.ok(shippings);
    }

    @GetMapping("/status/{status}")
    public ResponseEntity getShippingByStatus(@PathVariable Shipping.ShippingStatus status) {
        List<Shipping> shippings = shippingService.getShippingByStatus(status);
        return ResponseEntity.ok(shippings);
    }

    @GetMapping("/deliveryAddress/{deliveryAddress}")
    public ResponseEntity getShippingByDeliveryAddress(@PathVariable String deliveryAddress) {
        List<Shipping> shippings = shippingService.getShippingByDeliveryAddress(deliveryAddress);
        return ResponseEntity.ok(shippings);
    }

    @GetMapping("/expectedDate/{expected}")
    public ResponseEntity getShippingByExpectedDeliveryDate(@PathVariable Date expected) {
        List<Shipping> shippings = shippingService.getShippingByExpectedDeliveryDate(expected);
        return ResponseEntity.ok(shippings);
    }

}
