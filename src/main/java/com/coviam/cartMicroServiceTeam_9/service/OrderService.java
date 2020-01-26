package com.coviam.cartMicroServiceTeam_9.service;



import com.coviam.cartMicroServiceTeam_9.dto.*;
import com.coviam.cartMicroServiceTeam_9.entity.OrderDetails;
import com.coviam.cartMicroServiceTeam_9.entity.UserOrder;

import javax.mail.MessagingException;
import java.util.ArrayList;
import java.util.List;

public interface OrderService {
    UserOrder save(UserOrder userOrder);

    void sendEmail(int orderId, String userEmail, double amount, String orderDate, ArrayList<EmailOrderDetailsDTO> emailOrderDetailsDTOSList) throws MessagingException;
    StatusCheckForOrderDTO  checkQuantity(List<OrderDetails> orderDetails);
    void decreaseQuantityAfterOrderPlaced(ArrayList<DecreaseMerchantProductQuantityDTO> list);

    List<UserOrder> getUserOrderDetails(String userEmail);

}
