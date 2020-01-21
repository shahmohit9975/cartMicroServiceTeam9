package com.coviam.cartMicroService.controller;

import com.coviam.cartMicroService.dto.EmailOrderDetailsDTO;
import com.coviam.cartMicroService.entity.OrderDetails;
import com.coviam.cartMicroService.entity.UserOrder;
import com.coviam.cartMicroService.service.OrderService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.web.bind.annotation.*;

import javax.mail.BodyPart;
import javax.validation.Valid;
import java.util.ArrayList;
import javax.mail.MessagingException;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;


@CrossOrigin
@RestController
@RequestMapping("/order")
public class OrderController {

    @Autowired
    OrderService orderService;


    @PostMapping(path = "/place")
    private UserOrder placeOrder(@Valid @RequestBody UserOrder userOrder) {
        final UserOrder save = orderService.save(userOrder);
        ArrayList<EmailOrderDetailsDTO> emailOrderDetailsDTOSList = new ArrayList<EmailOrderDetailsDTO>();
        for (OrderDetails o : userOrder.getOrderDetails()) {
            EmailOrderDetailsDTO emailOrderDetailsDTO = new EmailOrderDetailsDTO();
            BeanUtils.copyProperties(o, emailOrderDetailsDTO);
            emailOrderDetailsDTOSList.add(emailOrderDetailsDTO);
        }
        try {
            orderService.sendEmail(save.getOrderId(), save.getUserEmail(), save.getAmount(), save.getOrderDate(), emailOrderDetailsDTOSList);
        } catch (MessagingException e) {
            e.printStackTrace();
        }
        return save;
    }
}
