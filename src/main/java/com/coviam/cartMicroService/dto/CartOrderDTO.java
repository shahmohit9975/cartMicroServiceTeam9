package com.coviam.cartMicroService.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter
@Setter
@ToString
public class CartOrderDTO {

//"userEmail":,
//        "amount":,
//        "orderDate":,
//
//    OrderDetails:[
//
//    {
//        "merchantAndProductId":,
//        "price":,
//        "quantity":
//    }
//    ],
//            "merchantName":,
//            "description":,
//            "productRating":,
//            "categoryName":,
//            "price":,
//            "sellingPrice":

    private int cartId;
    private String userEmail;
    private double amount;
    private String orderDate;
    private List<CartOrderDetailsDTO> orderDetails;

}
