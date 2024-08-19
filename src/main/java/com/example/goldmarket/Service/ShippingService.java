package com.example.goldmarket.Service;

import com.example.goldmarket.ApiException.ApiException;
import com.example.goldmarket.Model.Shipping;
import com.example.goldmarket.Repository.ShippingRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ShippingService {

    private final ShippingRepository shippingRepository;

    public List<Shipping> getAllShipping(){
        return shippingRepository.findAll();
    }

    public void addShipping(Shipping shipping){
        shippingRepository.save(shipping);
    }

    public void updateShipping(Long id, Shipping shipping){
        Shipping shipment = shippingRepository.findByShippingId(id);
        if(shipment == null){
            throw new ApiException("Shipment not found");
        }
        shipment.setShippingCost(shipping.getShippingCost());
        shipment.setCarrierName(shipping.getCarrierName());
        shipment.setStatus(shipping.getStatus());
        shipment.setDeliveryAddress(shipping.getDeliveryAddress());
        shipment.setExpectedDeliveryDate(shipping.getExpectedDeliveryDate());
        shipment.setTrackingNumber(shipping.getTrackingNumber());
        shipment.setOrderItems(shipping.getOrderItems());
        shippingRepository.save(shipment);
    }

    public void deleteShipping(Long id){
        Shipping shipping = shippingRepository.findByShippingId(id);
        if(shipping == null){
            throw new ApiException("Shipment not found");
        }
        shippingRepository.delete(shipping);
    }

    public Shipping getbyShippingId(Long id){
        Shipping shipping = shippingRepository.findByShippingId(id);
        if(shipping == null){
            throw new ApiException("Shipment not found");
        }
        return shipping;
    }

    public Shipping getbyTrackingNumber(String trackingNumber){
        Shipping shipping = shippingRepository.findByTrackingNumber(trackingNumber);
        if(shipping == null){
            throw new ApiException("Shipment not found");
        }
        return shipping;
    }

    public List<Shipping> getShippingByCarrierName(String carrierName){
        List<Shipping> shippings = shippingRepository.findByCarrierName(carrierName);
        if(shippings == null){
            throw new ApiException("Shipment not found");
        }
        return shippings;
    }

    public List<Shipping> getShippingByStatus(Shipping.ShippingStatus status){
        List<Shipping> shippings = shippingRepository.findByStatus(status);
        if(shippings == null){
            throw new ApiException("Shipment not found");
        }
        return shippings;
    }

    public List<Shipping> getShippingByDeliveryAddress(String deliveryAddress){
        List<Shipping> shippings = shippingRepository.findByDeliveryAddress(deliveryAddress);
        if(shippings == null){
            throw new ApiException("Shipment not found");
        }
        return shippings;
    }

    public List<Shipping> getShippingByExpectedDeliveryDate(Date expectedDeliveryDate){
        List<Shipping> shippings = shippingRepository.findByExpectedDeliveryDate(expectedDeliveryDate);
        if(shippings == null){
            throw new ApiException("Shipment not found");
        }
        return shippings;
    }
}
