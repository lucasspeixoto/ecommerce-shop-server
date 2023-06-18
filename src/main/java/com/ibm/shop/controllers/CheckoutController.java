package com.ibm.shop.controllers;

import com.ibm.shop.data.vo.Purchase;
import com.ibm.shop.data.vo.PurchaseResponse;
import com.ibm.shop.services.CheckoutService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "Checkout Endpoint")
@RestController
@RequestMapping("/checkout")
public class CheckoutController {

    @Autowired
    CheckoutService checkoutService;

    @PostMapping("/purchase")
    public PurchaseResponse placeOrder(@RequestBody Purchase purchase) {

        return checkoutService.placeOrder(purchase);
    }
}
