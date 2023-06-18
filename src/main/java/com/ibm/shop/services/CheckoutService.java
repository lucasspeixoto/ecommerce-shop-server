package com.ibm.shop.services;

import com.ibm.shop.data.vo.Purchase;
import com.ibm.shop.data.vo.PurchaseResponse;
import com.ibm.shop.entities.Order;
import com.ibm.shop.entities.OrderItem;
import com.ibm.shop.entities.User;
import com.ibm.shop.repositories.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.UUID;

@Service
public class CheckoutService {

    @Autowired
    UserRepository userRepository;

    @Transactional
    public PurchaseResponse placeOrder(Purchase purchase) {

        // retrieve the order info from dto
        Order order = purchase.getOrder();

        // generate tracking number
        String orderTrackingNumber = generatedOrderTrackingNumber();
        order.setOrderTrackingNumber(orderTrackingNumber);

        // populate order with order items
        Set<OrderItem> orderItems = purchase.getOrderItems();
        orderItems.forEach(order::add); // order::add = item -> order.add(item)

        // populate order with billingAddress and shippingAddress
        order.setBillingAddress(purchase.getBillingAddress());
        order.setShippingAddress(purchase.getShippingAddress());

        // populate customer with order
        User customer = purchase.getCustomer();

        // check if this is an existing customer
        String email = customer.getEmail();
        User customerFromDB = userRepository.findByEmail(email);
        if (customerFromDB != null) {
            //found them... let`s assign them accordingly
            customer = customerFromDB;
        }
        customer.add(order);

        // save to database
        userRepository.save(customer);

        // return response
        return new PurchaseResponse(orderTrackingNumber);
    }

    private String generatedOrderTrackingNumber() {

        // generate a random UUID number (UUID version-4)
        return UUID.randomUUID().toString();
    }
}
