package com.coviam.cartMicroService.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter
@Setter
@ToString
public class CartDTO {


    private String userEmail;//
    private List<AllCartDetailsDTO> allCartDetailsDTOS;
}
