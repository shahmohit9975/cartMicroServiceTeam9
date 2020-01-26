package com.coviam.cartMicroServiceTeam_9.dto;

import com.coviam.cartMicroServiceTeam_9.entity.OrderDetails;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.CascadeType;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import java.util.List;

@Getter
@Setter
@ToString
public class UserOrderDTO {
    private int orderId;
    private String userEmail;
    private double amount;
    private String orderDate;
    private int totalOrderItemsQuantity;

}
