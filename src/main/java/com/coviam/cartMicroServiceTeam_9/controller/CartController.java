package com.coviam.cartMicroServiceTeam_9.controller;


import com.coviam.cartMicroServiceTeam_9.dto.CartDTO;
import com.coviam.cartMicroServiceTeam_9.dto.CartUpdateDTO;
import com.coviam.cartMicroServiceTeam_9.dto.EmailDTO;
import com.coviam.cartMicroServiceTeam_9.entity.Cart;
import com.coviam.cartMicroServiceTeam_9.service.CartService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/cart")
public class CartController {

    @Autowired
    CartService cartService;

    @PutMapping(path = "/add")
    public ResponseEntity<Cart> updateCartDetails(@Valid @RequestBody CartUpdateDTO cartUpdateDTO) {
        Cart cart = new Cart();
        System.out.println("-->" + cartUpdateDTO.getCartQuantity());
        BeanUtils.copyProperties(cartUpdateDTO, cart);
        return new ResponseEntity<Cart>(cartService.updateCart(cart), HttpStatus.OK);
    }

    @PostMapping(path = "/get")
    public ResponseEntity<?> getCartList(@RequestHeader Map<String, String> headerss, @Valid @RequestBody EmailDTO emailDTO, HttpServletResponse response, HttpServletRequest request) {
        System.out.println("inside cart get...");

        headerss.forEach((key, value) -> {
            System.out.println(String.format("Header '%s' = %s", key, value));
        });
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            Arrays.stream(cookies)
                    .forEach(c -> System.out.println(c.getName() + "=================" + c.getValue()));
        }
        return new ResponseEntity<>(cartService.getAllCartDetailsForUser(emailDTO.getUserEmail()).getAllCartDetailsDTOS(), HttpStatus.OK);
    }

    @DeleteMapping(path = "/delete/{cartId}")
    public ResponseEntity<Map<String, ?>> deleteCartList(@PathVariable int cartId) {
        System.out.println("inside delete cart...");
        return new ResponseEntity<Map<String, ?>>(cartService.deleteCartList(cartId), HttpStatus.OK);
    }
}
