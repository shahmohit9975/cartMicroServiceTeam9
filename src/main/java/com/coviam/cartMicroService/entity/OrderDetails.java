package com.coviam.cartMicroService.entity;

import javax.persistence.*;


@Entity
public class OrderDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int orderDetailsId;
    private String merchantAndProductId;
    private double price;
    private int quantity;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "userOrderId")
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
