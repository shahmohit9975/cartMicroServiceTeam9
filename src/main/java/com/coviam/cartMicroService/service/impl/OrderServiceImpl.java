package com.coviam.cartMicroService.service.impl;

import com.coviam.cartMicroService.dto.EmailOrderDetailsDTO;
import com.coviam.cartMicroService.entity.OrderDetails;
import com.coviam.cartMicroService.entity.UserOrder;
import com.coviam.cartMicroService.repository.UserOrderRepository;
import com.coviam.cartMicroService.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.BodyPart;
import javax.mail.MessagingException;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import java.util.ArrayList;
import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {
    @Autowired
    UserOrderRepository userOrderRepository;
    @Autowired
    private JavaMailSender javaMailSender;

    @Override
    public UserOrder save(UserOrder userOrder) {

        List<OrderDetails> orderDetails = userOrder.getOrderDetails();
        for (OrderDetails orderDetails1 : orderDetails)
            orderDetails1.setUserOrder(userOrder);
        final UserOrder save = userOrderRepository.save(userOrder);


        return save;
    }

    @Override
    public void sendEmail(int orderId, String userEmail, double amount, String orderDate, ArrayList<EmailOrderDetailsDTO> emailOrderDetailsDTOSList) throws MessagingException {
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, "utf-8");
        helper.setTo(userEmail);
        helper.setSubject("Your Order Details");
        String orderMsg = null;
        orderMsg = "<br>orderId : " + orderId + "<br>Total amount : " + amount + "<br>orderDate : " + orderDate + "<br><br>";
        for (EmailOrderDetailsDTO emailOrderDetailsDTO : emailOrderDetailsDTOSList) {
            orderMsg += "<br>CategoryName : " + emailOrderDetailsDTO.getCategoryName();
            orderMsg += "<br>Description : " + emailOrderDetailsDTO.getDescription();
            orderMsg += "<br>MerchantName : " + emailOrderDetailsDTO.getMerchantName();
            orderMsg += "<br> Quantity :" + emailOrderDetailsDTO.getQuantity();
            orderMsg += "<br> Original Price :" + emailOrderDetailsDTO.getPrice();
            orderMsg += "<br> Selling Price :" + emailOrderDetailsDTO.getSellingPrice();

            BodyPart messageBodyPart = new MimeBodyPart();
            orderMsg += "<br><img src='https://images.fastcompany.net/image/upload/w_596,c_limit,q_auto:best,f_auto/fc/3034007-inline-i-applelogo.jpg' alt='Smiley face' height='100' width='100'>";
            orderMsg += "<br><b>===============================</b><br>";
        }
        helper.setText("" + orderMsg + "", true);
        javaMailSender.send(mimeMessage);
    }
}
