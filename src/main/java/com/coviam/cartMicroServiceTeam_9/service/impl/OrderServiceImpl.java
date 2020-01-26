package com.coviam.cartMicroServiceTeam_9.service.impl;


import com.coviam.cartMicroServiceTeam_9.dto.*;
import com.coviam.cartMicroServiceTeam_9.entity.OrderDetails;
import com.coviam.cartMicroServiceTeam_9.entity.UserOrder;
import com.coviam.cartMicroServiceTeam_9.repository.UserOrderRepository;
import com.coviam.cartMicroServiceTeam_9.service.OrderService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

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
        //TODO add userEmail
        helper.setTo("201812093@daiict.ac.in");
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

    @Override
    public synchronized StatusCheckForOrderDTO checkQuantity(List<OrderDetails> orderDetails) {
        StatusCheckForOrderDTO statusCheckForOrderDTO = new StatusCheckForOrderDTO();
        ArrayList<QuantityCheckForOrderDTO> quantityCheckForOrderDTOList = new ArrayList<QuantityCheckForOrderDTO>();
        statusCheckForOrderDTO.setStatus(true);
        for (OrderDetails od : orderDetails) {
            QuantityCheckForOrderDTO quantityCheckForOrderDTO = new QuantityCheckForOrderDTO();
            String merchantAndProductId = od.getMerchantAndProductId();
            final String uri = "http://localhost:8082/merchantAndProduct/get/merchant/" + merchantAndProductId;
            System.out.println("URL : " + uri);

            RestTemplate restTemplate = new RestTemplate();
            ResponseEntity<MerchantAndProductDTO> responseEntity = restTemplate.exchange(
                    uri,
                    HttpMethod.GET,
                    null,
                    new ParameterizedTypeReference<MerchantAndProductDTO>() {
                    });
            MerchantAndProductDTO merchantAndProductDTO = responseEntity.getBody();
            int availableQuantity = merchantAndProductDTO.getQuantity() - merchantAndProductDTO.getTotalSellingQuantity();
            if (availableQuantity < od.getQuantity()) {
                statusCheckForOrderDTO.setStatus(false);
                quantityCheckForOrderDTO.setAvailableQuantity(availableQuantity);
                quantityCheckForOrderDTO.setMerchantAndProductId(merchantAndProductId);
                quantityCheckForOrderDTOList.add(quantityCheckForOrderDTO);
            }

        }
        statusCheckForOrderDTO.setQuantityCheckForOrderDTOList(quantityCheckForOrderDTOList);
        return statusCheckForOrderDTO;
    }

    @Override
    public void decreaseQuantityAfterOrderPlaced(ArrayList<DecreaseMerchantProductQuantityDTO> list) {
        final String uri = "http://localhost:8082/merchantAndProduct/decreaseQuantity";
        System.out.println("URL : " + uri);
        RestTemplate restTemplate = new RestTemplate();
        String responseMessage = restTemplate.postForObject(
                uri, list, String.class
        );
    }

    @Override
    public List<UserOrder> getUserOrderDetails(String userEmail) {
        List<UserOrder> userOrderList = userOrderRepository.findAllByUserEmail(userEmail);

        return userOrderList;
    }

}
