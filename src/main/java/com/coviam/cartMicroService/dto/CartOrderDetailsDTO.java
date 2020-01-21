package com.coviam.cartMicroService.dto;


import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class CartOrderDetailsDTO {
    private int orderDetailsId;
    private String merchantAndProductId;
    private String merchantName;
    private double price;
    private double sellingPrice;
    private int quantity;
    private String description;
    private String categoryName;
    private CartOrderDTO userOrder;
}
