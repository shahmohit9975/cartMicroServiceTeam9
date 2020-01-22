package com.coviam.cartMicroService.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter
@Setter
@ToString
public class CartOrderDTO {

    private int cartId;
    private String userEmail;//
    private double amount;
    private String orderDate;
    private List<CartOrderDetailsDTO> orderDetails;

}
