package com.ibm.shop.data.vo;

import com.ibm.shop.entities.Address;
import com.ibm.shop.entities.Order;
import com.ibm.shop.entities.OrderItem;
import com.ibm.shop.entities.User;

import java.util.Objects;
import java.util.Set;

public class Purchase {

    private User customer;

    private Address shippingAddress;

    private Address billingAddress;

    private Order order;

    private Set<OrderItem> orderItems;

    public User getCustomer() {
        return customer;
    }

    public void setCustomer(User customer) {
        this.customer = customer;
    }

    public Address getShippingAddress() {
        return shippingAddress;
    }

    public void setShippingAddress(Address shippingAddress) {
        this.shippingAddress = shippingAddress;
    }

    public Address getBillingAddress() {
        return billingAddress;
    }

    public void setBillingAddress(Address billingAddress) {
        this.billingAddress = billingAddress;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public Set<OrderItem> getOrderItems() {
        return orderItems;
    }

    public void setOrderItems(Set<OrderItem> orderItems) {
        this.orderItems = orderItems;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Purchase purchase)) return false;
        return getCustomer().equals(purchase.getCustomer()) && getShippingAddress().equals(purchase.getShippingAddress()) && getBillingAddress().equals(purchase.getBillingAddress()) && getOrder().equals(purchase.getOrder()) && getOrderItems().equals(purchase.getOrderItems());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getCustomer(), getShippingAddress(), getBillingAddress(), getOrder(), getOrderItems());
    }
}
