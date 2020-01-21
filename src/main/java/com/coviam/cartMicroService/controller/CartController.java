package com.coviam.cartMicroService.controller;

import com.coviam.cartMicroService.dto.AllCartDetailsDTO;
import com.coviam.cartMicroService.dto.CartUpdateDTO;
import com.coviam.cartMicroService.entity.Cart;
import com.coviam.cartMicroService.service.CartService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@CrossOrigin
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

    @GetMapping(path = "/get/{userEmail}")
    public ResponseEntity<List<AllCartDetailsDTO>> getCartList(@PathVariable String userEmail) {

        return new ResponseEntity<List<AllCartDetailsDTO>>(cartService.getAllCartDetails(userEmail), HttpStatus.OK);
    }
}
