package com.ibm.shop.exceptions;

import org.springframework.http.HttpStatus;

public class IbmShopApiException extends RuntimeException {

    private final HttpStatus status;

    private final String message;

    public IbmShopApiException(HttpStatus status, String message) {
        this.status = status;
        this.message = message;
    }

    public IbmShopApiException(String message, HttpStatus status, String message1) {
        super(message);
        this.status = status;
        this.message = message1;
    }

    public HttpStatus getStatus() {
        return status;
    }

    @Override
    public String getMessage() {
        return message;
    }
}

