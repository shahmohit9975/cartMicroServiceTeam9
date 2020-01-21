package com.coviam.cartMicroService.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class AllCartDetailsDTO {
    private String _id;
    private String merchantAndProductId;
    private String productId;
    private String merchantId;
    private String productName;
    private String description;
    private double productRating;
    private String categoryName;
    private double price;
    private double sellingPrice;
    private String url1;
    private String url2;
    private String url3;
}
