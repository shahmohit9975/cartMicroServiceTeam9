package com.coviam.cartMicroServiceTeam_9.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class DecreaseMerchantProductQuantityDTO {
    private String merchantAndProductId;
//    private String merchantId;
    private int quantity;
}
