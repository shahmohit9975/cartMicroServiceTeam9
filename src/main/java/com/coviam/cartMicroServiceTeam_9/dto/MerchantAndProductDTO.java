package com.coviam.cartMicroServiceTeam_9.dto;


import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class MerchantAndProductDTO {
    private String merchantAndProductId;
    private String merchantId;
    private String productId;
    private int quantity;
    private double sellingPrice;
    private int totalSellingQuantity;

    //    private String merchantAndProductId;
//    private String merchantId;
//    private String productId;
//    private int quantity;
//    private double sellingPrice;
//    private int totalSellingQuantity;
    private double revenue;


}
