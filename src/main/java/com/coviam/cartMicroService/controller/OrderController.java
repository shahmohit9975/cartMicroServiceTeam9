package com.coviam.cartMicroService.controller;

import com.coviam.cartMicroService.entity.UserOrder;
import com.coviam.cartMicroService.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/order")
public class OrderController {

    @Autowired
    OrderService orderService;

    @PostMapping(path = "/place")
    private UserOrder placeOrder(@Valid @RequestBody UserOrder userOrder) {
        System.out.println("********>" + userOrder.toString());

        return orderService.save(userOrder);

    }
}
