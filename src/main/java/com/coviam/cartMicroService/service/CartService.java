package com.coviam.cartMicroService.service;

import com.coviam.cartMicroService.dto.AllCartDetailsDTO;
import com.coviam.cartMicroService.entity.Cart;

import java.util.List;

public interface CartService {

    Cart updateCart(Cart cart);

    List<AllCartDetailsDTO> getAllCartDetails(String userEmail);
}
