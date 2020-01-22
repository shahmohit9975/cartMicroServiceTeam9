package com.coviam.cartMicroService.service.impl;

import com.coviam.cartMicroService.dto.AllCartDetailsDTO;
import com.coviam.cartMicroService.dto.CartDTO;
import com.coviam.cartMicroService.entity.Cart;
import com.coviam.cartMicroService.repository.CartRepository;
import com.coviam.cartMicroService.service.CartService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
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
        obj.setCartQuantity(obj.getCartQuantity() + cart.getCartQuantity());
        cartRepository.save(obj);
        return obj;
    }

    @Override
    public List<CartDTO> getAllCartDetails(String userEmail) {
        List<Cart> cartList = cartRepository.findByUserEmail(userEmail);

        CartDTO cartDTO = new CartDTO();
        cartDTO.setUserEmail(userEmail);
        for (Cart cart : cartList) {
            String merchantAndProductId = cart.getMerchantAndProductId();

            AllCartDetailsDTO allCartDetailsDTO = new AllCartDetailsDTO();
            allCartDetailsDTO.setCartId(cart.getCartId());
            allCartDetailsDTO.setCartQuantity(cart.getCartQuantity());
            allCartDetailsDTO.setMerchantAndProductId(cart.getMerchantAndProductId());


            StringBuffer response = new StringBuffer();
            try {
                URL url = new URL("http://localhost:8082/merchantAndProduct/getCartDetails/" + merchantAndProductId);
                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                conn.setRequestMethod("GET");
                conn.setRequestProperty("Accept", "application/json");
                if (conn.getResponseCode() != 200) {
                    throw new RuntimeException("Failed : HTTP error code : " + conn.getResponseCode());
                }

                BufferedReader br = new BufferedReader(new InputStreamReader((conn.getInputStream())));

                String output;
                while ((output = br.readLine()) != null) {
                    response.append(output);
                }
                br.close();
                conn.disconnect();
            } catch (MalformedURLException e) {

                e.printStackTrace();

            } catch (IOException e) {

                e.printStackTrace();

            }


            System.out.println("######################## > " + response.toString());

        }
        return new ArrayList<CartDTO>();
    }
}
