package com.coviam.cartMicroServiceTeam_9.service;



import com.coviam.cartMicroServiceTeam_9.dto.EmailOrderDetailsDTO;
import com.coviam.cartMicroServiceTeam_9.entity.UserOrder;

import javax.mail.MessagingException;
import java.util.ArrayList;

public interface OrderService {
    UserOrder save(UserOrder userOrder);

    void sendEmail(int orderId, String userEmail, double amount, String orderDate, ArrayList<EmailOrderDetailsDTO> emailOrderDetailsDTOSList) throws MessagingException;
}
