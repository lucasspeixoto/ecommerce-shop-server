package com.ibm.shop.data.vo;

import java.util.Objects;

public class PurchaseResponse {

    private String orderTrackingNumber;

    public PurchaseResponse(String orderTrackingNumber) {
        this.orderTrackingNumber = orderTrackingNumber;
    }

    public String getOrderTrackingNumber() {
        return orderTrackingNumber;
    }

    public void setOrderTrackingNumber(String orderTrackingNumber) {
        this.orderTrackingNumber = orderTrackingNumber;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof PurchaseResponse that)) return false;
        return getOrderTrackingNumber().equals(that.getOrderTrackingNumber());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getOrderTrackingNumber());
    }
}
