package com.coviam.cartMicroService.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Getter
@Setter
@ToString
@Entity
public class UserOrder {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int orderId;
    private String userEmail;
    private double amount;
    private String orderDate;
    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "userOrder")

    private List<OrderDetails> orderDetails;

    public void setOrderDate(String orderDate) {
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter format = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
        String formatDateTime = now.format(format);
        this.orderDate = formatDateTime;
    }

}
