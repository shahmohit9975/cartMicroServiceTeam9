package com.coviam.cartMicroService.service;

import com.coviam.cartMicroService.entity.UserOrder;

public interface OrderService {
    UserOrder save(UserOrder userOrder);
}
