package com.coviam.cartMicroService.service.impl;

import com.coviam.cartMicroService.entity.OrderDetails;
import com.coviam.cartMicroService.entity.UserOrder;
import com.coviam.cartMicroService.repository.UserOrderRepository;
import com.coviam.cartMicroService.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {
    @Autowired
    UserOrderRepository userOrderRepository;

    @Override
    public UserOrder save(UserOrder userOrder) {
//        userOrder.getOrderDetails();
//        final UserOrder save = userOrderRepository.save(userOrder);
        List<OrderDetails> orderDetails = userOrder.getOrderDetails();
        for(OrderDetails orderDetails1:orderDetails)
            orderDetails1.setUserOrder(userOrder);
        return userOrderRepository.save(userOrder);
    }
}
