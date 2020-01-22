package com.coviam.cartMicroService.controller;

import com.coviam.cartMicroService.dto.AllCartDetailsDTO;
import com.coviam.cartMicroService.dto.CartDTO;
import com.coviam.cartMicroService.dto.CartUpdateDTO;
import com.coviam.cartMicroService.dto.EmailDTO;
import com.coviam.cartMicroService.entity.Cart;
import com.coviam.cartMicroService.service.CartService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/cart")
public class CartController {

    @Autowired
    CartService cartService;

    @PutMapping(path = "/add")
    public ResponseEntity<Cart> updateCartDetails(@Valid @RequestBody CartUpdateDTO cartUpdateDTO) {
        Cart cart = new Cart();
        BeanUtils.copyProperties(cartUpdateDTO, cart);
        return new ResponseEntity<Cart>(cartService.updateCart(cart), HttpStatus.OK);
    }

    @PostMapping(path = "/get")
    public ResponseEntity<List<CartDTO>> getCartList(@RequestBody EmailDTO emailDTO) {

        return new ResponseEntity<List<CartDTO>>(cartService.getAllCartDetails(emailDTO.getUserEmail()), HttpStatus.OK);
    }
}
