package com.coviam.cartMicroServiceTeam_9.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class EmailOrderDetailsDTO {
    private double price;
    private int quantity;
    private String merchantName;
    private double sellingPrice;
    private String description;
    private String categoryName;

}
