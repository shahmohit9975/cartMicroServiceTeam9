package com.coviam.cartMicroService.dto;

import com.coviam.cartMicroService.entity.UserOrder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Transient;

@Getter
@Setter
@ToString
public class AllCartDetailsDTO {
    private int cartId;//

    private int cartQuantity;
    private String merchantAndProductId;

    private double sellingPrice;

    private String productId;
    private String productName;
    private String description;
    private double productRating;
    private String categoryName;
    private String url1;
    private double price;


    private String merchantName;


}
