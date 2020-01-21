package com.coviam.cartMicroService.service.impl;

import com.coviam.cartMicroService.dto.AllCartDetailsDTO;
import com.coviam.cartMicroService.entity.Cart;
import com.coviam.cartMicroService.repository.CartRepository;
import com.coviam.cartMicroService.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CartServiceImpl implements CartService {

    @Autowired
    CartRepository cartRepository;

    @Override
    public Cart updateCart(Cart cart) {
        Cart obj = cartRepository.findCart(cart.getUserEmail(), cart.getMerchantAndProductId());
        if (obj == null) {
            return cartRepository.save(cart);
        }
        obj.setQuantity(obj.getQuantity() + cart.getQuantity());
        cartRepository.save(obj);
        return obj;
    }

    @Override
    public List<AllCartDetailsDTO> getAllCartDetails(String userEmail) {
        List<Cart> cartList = cartRepository.findByUserEmail(userEmail);

        return null;
    }
}
