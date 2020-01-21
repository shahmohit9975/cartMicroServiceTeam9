package com.coviam.cartMicroService.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CartUpdateDTO {
    private int cartId;
    private String userEmail;
    private int quantity;
    private String merchantAndProductId;
}
