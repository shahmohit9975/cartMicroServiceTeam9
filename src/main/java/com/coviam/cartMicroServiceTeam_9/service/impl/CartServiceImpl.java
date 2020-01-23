package com.coviam.cartMicroServiceTeam_9.service.impl;

import com.coviam.cartMicroServiceTeam_9.dto.AllCartDetailsDTO;
import com.coviam.cartMicroServiceTeam_9.dto.CartDTO;
import com.coviam.cartMicroServiceTeam_9.entity.Cart;
import com.coviam.cartMicroServiceTeam_9.repository.CartRepository;
import com.coviam.cartMicroServiceTeam_9.service.CartService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
        obj.setCartQuantity(obj.getCartQuantity() + cart.getCartQuantity());
        cartRepository.save(obj);
        return obj;
    }


    @Override
    public CartDTO getAllCartDetailsForUser(String userEmail) {

        List<Cart> cartList = cartRepository.findByUserEmail(userEmail);
        System.out.println("cart table : " + cartList.toString());
        List<AllCartDetailsDTO> cartDTOList = new ArrayList<AllCartDetailsDTO>();
        CartDTO cartDTO = new CartDTO();
        cartDTO.setUserEmail(userEmail);
        for (Cart cart : cartList) {
            System.out.println("inside for :" + cart.toString());
            String merchantAndProductId = cart.getMerchantAndProductId();
            final String uri = "http://localhost:8082/merchantAndProduct/getCartDetails/" + merchantAndProductId;
            System.out.println("URL : " + uri);
            RestTemplate restTemplate = new RestTemplate();


            ResponseEntity<AllCartDetailsDTO> responseEntity = restTemplate.exchange(
                    uri,
                    HttpMethod.GET,
                    null,
                    new ParameterizedTypeReference<AllCartDetailsDTO>() {
                    });
            AllCartDetailsDTO allCartDetailsDTO = responseEntity.getBody();
            System.out.println("get AllCartDetailsDTO : " + allCartDetailsDTO.toString());
            BeanUtils.copyProperties(cart, allCartDetailsDTO);

            cartDTOList.add(allCartDetailsDTO);
        }

        cartDTO.setAllCartDetailsDTOS(cartDTOList);
        return cartDTO;

    }

    @Override
    public Map<String, ?> deleteCartList(int cartId) {
        Map<String, Boolean> map = new HashMap<String, Boolean>();
        try {
            cartRepository.deleteById(cartId);
        } catch (Exception ex) {
            map.put("status", false);
            return map;
        }
        map.put("status", true);
        return map;
    }
}
