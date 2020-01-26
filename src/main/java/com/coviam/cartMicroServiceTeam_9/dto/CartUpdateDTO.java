package com.coviam.cartMicroServiceTeam_9.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CartUpdateDTO {
    private int cartId;
    private String userEmail;
    private int cartQuantity;
    private String merchantAndProductId;

}
