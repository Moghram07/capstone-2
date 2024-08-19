package com.example.goldmarket.Service;

import com.example.goldmarket.ApiException.ApiException;
import com.example.goldmarket.Model.*;
import com.example.goldmarket.Repository.CustomerRepository;
import com.example.goldmarket.Repository.ProductRepository;
import com.example.goldmarket.Repository.OrderItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderItemService {

    private final OrderItemRepository orderItemRepository;
    private final ProductService productService;
    private final CustomerService customerService;
    private final InventoryService inventoryService;
    private final ShippingService shippingService;

    public List<OrderItem> getAllOrderItems() {
        return orderItemRepository.findAll();
    }

    public void addOrderItem(OrderItem orderItem) {
        // Ensure Shipping is saved if provided
        if (orderItem.getShippingDetails() != null) {
            Shipping shipping = orderItem.getShippingDetails();
            if (shipping.getShippingId() == null) {
                shippingService.addShipping(shipping); // Save new Shipping
            }
        }
        orderItemRepository.save(orderItem);
    }

    public void updateOrderItem(Long id, OrderItem orderItem) {
        OrderItem items = orderItemRepository.findByItemId(id);
        if(items == null) {
            throw new ApiException("Item Not Found");
        }
        items.setQuantity(orderItem.getQuantity());
        items.setProduct(orderItem.getProduct());
        items.setCustomer(orderItem.getCustomer());
        orderItemRepository.save(items);
    }

    public void deleteOrderItem(Long id) {
        OrderItem items = orderItemRepository.findByItemId(id);
        if(items == null) {
            throw new ApiException("Item Not Found");
        }
        orderItemRepository.delete(items);
    }

    public void buyProduct(Long productId, Long customerId, int quantity) {
        Product product = productService.getById(productId);
        if(product == null) {
            throw new ApiException("Product not found");
        }

        Customer customer = customerService.getById(customerId);
        if(customer == null) {
            throw new ApiException("Customer not found");
        }

        Inventory inventory = inventoryService.getInventoryByProductId(productId);
        if(inventory == null || inventory.getQuantity() < quantity) {
            throw new ApiException("product is out of stock");
        }

        double totalPrice = product.getPrice() * quantity;
        if (customer.getBalance() < totalPrice) {
            throw new ApiException("Insufficient balance");
        }

        // Reduce stock
        inventory.setQuantity(inventory.getQuantity() - quantity);
        inventoryService.updateInventory(inventory.getId(), inventory);

        // Update customer balance
        customer.setBalance(customer.getBalance() - totalPrice);
        customerService.updateCustomer(customer.getId(), customer);

        // Create order item
        OrderItem orderItem = new OrderItem();
        orderItem.setProduct(product);
        orderItem.setCustomer(customer);
        orderItem.setQuantity(quantity);

        // Optionally, create and set a new Shipping
        Shipping shipping = new Shipping();
        shipping.setCarrierName("Carrier Example");
        shipping.setTrackingNumber("TRACK123456");
        shipping.setShippingCost(10.0);
        shipping.setDeliveryAddress("123 Example St");
        shipping.setExpectedDeliveryDate(new Date());
        shipping.setStatus(Shipping.ShippingStatus.INITIATED);
        shippingService.addShipping(shipping);

        orderItem.setShippingDetails(shipping);
        orderItemRepository.save(orderItem);
    }
}
