package com.coviam.cartMicroServiceTeam_9.dto;

import com.coviam.cartMicroServiceTeam_9.entity.OrderDetails;
import com.coviam.cartMicroServiceTeam_9.entity.UserOrder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Transient;
import java.util.List;


@ToString
public class OrderDetailsDTO {
    private int orderDetailsId;
    private String merchantAndProductId;
    private double price;
    private int quantity;
    private UserOrder userOrder;

    public int getOrderDetailsId() {
        return orderDetailsId;
    }

    public void setOrderDetailsId(int orderDetailsId) {
        this.orderDetailsId = orderDetailsId;
    }

    public String getMerchantAndProductId() {
        return merchantAndProductId;
    }

    public void setMerchantAndProductId(String merchantAndProductId) {
        this.merchantAndProductId = merchantAndProductId;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public void setUserOrder(UserOrder userOrder) {
        this.userOrder = userOrder;
    }
}
