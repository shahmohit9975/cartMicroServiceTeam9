package com.coviam.cartMicroServiceTeam_9.service;

import com.coviam.cartMicroServiceTeam_9.dto.CartDTO;
import com.coviam.cartMicroServiceTeam_9.entity.Cart;

import java.util.List;
import java.util.Map;

public interface CartService {

    Cart updateCart(Cart cart);

    CartDTO getAllCartDetailsForUser(String userEmail);

    Map<String, ?> deleteCartList(int cartId);
}
