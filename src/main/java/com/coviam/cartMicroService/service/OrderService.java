package com.coviam.cartMicroService.service;

import com.coviam.cartMicroService.dto.EmailOrderDetailsDTO;
import com.coviam.cartMicroService.entity.UserOrder;

import javax.mail.MessagingException;
import java.util.ArrayList;

public interface OrderService {
    UserOrder save(UserOrder userOrder);

    void sendEmail(int orderId, String userEmail, double amount, String orderDate, ArrayList<EmailOrderDetailsDTO> emailOrderDetailsDTOSList) throws MessagingException;
}
