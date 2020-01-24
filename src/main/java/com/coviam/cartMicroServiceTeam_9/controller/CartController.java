package com.coviam.cartMicroServiceTeam_9.controller;


import com.coviam.cartMicroServiceTeam_9.dto.CartDTO;
import com.coviam.cartMicroServiceTeam_9.dto.CartUpdateDTO;
import com.coviam.cartMicroServiceTeam_9.dto.EmailDTO;
import com.coviam.cartMicroServiceTeam_9.entity.Cart;
import com.coviam.cartMicroServiceTeam_9.service.CartService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
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
        BeanUtils.copyProperties(cartUpdateDTO, cart);
        return new ResponseEntity<Cart>(cartService.updateCart(cart), HttpStatus.OK);
    }

    @PostMapping(path = "/get")
    public ResponseEntity<CartDTO> getCartList(@Valid @RequestBody EmailDTO emailDTO) {
        System.out.println("inside cart get...");
        return new ResponseEntity<CartDTO>(cartService.getAllCartDetailsForUser(emailDTO.getUserEmail()), HttpStatus.OK);
    }

    @DeleteMapping(path = "/delete")
    public ResponseEntity<Map<String, ?>> deleteCartList(@Valid @RequestBody Map<String, ?> map) {
        System.out.println("inside delete cart...");
        int cartId = Integer.parseInt(String.valueOf(map.get("cartId")));
        return new ResponseEntity<Map<String, ?>>(cartService.deleteCartList(cartId), HttpStatus.OK);
    }
}
