package com.coviam.cartMicroServiceTeam_9.controller;


import com.coviam.cartMicroServiceTeam_9.dto.EmailOrderDetailsDTO;
import com.coviam.cartMicroServiceTeam_9.entity.OrderDetails;
import com.coviam.cartMicroServiceTeam_9.entity.UserOrder;
import com.coviam.cartMicroServiceTeam_9.service.OrderService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.mail.MessagingException;
import javax.validation.Valid;
import java.util.ArrayList;


@CrossOrigin(origins = "*")
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
