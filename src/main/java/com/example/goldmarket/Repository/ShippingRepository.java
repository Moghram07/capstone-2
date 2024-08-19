package com.example.goldmarket.Repository;

import com.example.goldmarket.Model.Shipping;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;
import java.util.List;

public interface ShippingRepository extends JpaRepository<Shipping, Long> {

    Shipping findByShippingId(Long shippingId);

    Shipping findByTrackingNumber(String trackingNumber);

    List<Shipping> findByCarrierName(String carrierName);

    List<Shipping> findByDeliveryAddress(String deliveryAddress);

    List<Shipping> findByStatus(Shipping.ShippingStatus status);

    List<Shipping> findByExpectedDeliveryDate(Date expectedDeliveryDate);

}
